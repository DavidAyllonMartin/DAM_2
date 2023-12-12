package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_4;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.DataAccessException;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.DataIntegrityException;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.DuplicateKeyException;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.IncompatibleVersionException;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharacter;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions.InvalidStarWarsParameterException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StarWarsCharactersDaoDb implements StarWarsCharactersDAO{

    private final String url = "jdbc:sqlite:src/main/resources/sw.sqlite";

    @Override
    public void create(StarWarsCharacter character) throws DataAccessException, DuplicateKeyException {
        try (Connection connection = DriverManager.getConnection(url)) {
            String insertQuery = "INSERT INTO StarWarsCharacters (name, gender, birthYear, height, mass, hairColor, skinColor, eyeColor, planet, species) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, character.getName());
                preparedStatement.setString(2, character.getGender());
                preparedStatement.setString(3, character.getBirthYear());
                preparedStatement.setInt(4, character.getHeight());
                preparedStatement.setDouble(5, character.getMass());
                preparedStatement.setString(6, character.getHairColor());
                preparedStatement.setString(7, character.getSkinColor());
                preparedStatement.setString(8, character.getEyeColor());
                preparedStatement.setString(9, character.getPlanet());
                preparedStatement.setString(10, character.getSpecies());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new DuplicateKeyException("Star Wars character already exists: " + character.getName());
            } else {
                throw new DataAccessException("Error creating Star Wars character");
            }
        }
    }

    @Override
    public StarWarsCharacter read(String name) throws DataAccessException, IncompatibleVersionException {
        try (Connection connection = DriverManager.getConnection(url)) {
            String selectQuery = "SELECT * FROM StarWarsCharacters WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {

                        return new StarWarsCharacter.Builder()
                                .name(resultSet.getString("name"))
                                .gender(resultSet.getString("gender"))
                                .birthYear(resultSet.getString("birthYear"))
                                .height(resultSet.getInt("height"))
                                .mass(resultSet.getDouble("mass"))
                                .hairColor(resultSet.getString("hairColor"))
                                .skinColor(resultSet.getString("skinColor"))
                                .eyeColor(resultSet.getString("eyeColor"))
                                .planet(resultSet.getString("planet"))
                                .species(resultSet.getString("species"))
                                .build();
                    }
                }catch (InvalidStarWarsParameterException e){
                    throw new IncompatibleVersionException();
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error reading Star Wars character");
        }

        return null;
    }

    @Override
    public List<StarWarsCharacter> readAll() throws DataAccessException, IncompatibleVersionException {
        List<StarWarsCharacter> characters = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url)) {
            String selectAllQuery = "SELECT * FROM StarWarsCharacters";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        try {
                            StarWarsCharacter character = new StarWarsCharacter.Builder()
                                    .name(resultSet.getString("name"))
                                    .gender(resultSet.getString("gender"))
                                    .birthYear(resultSet.getString("birthYear"))
                                    .height(resultSet.getInt("height"))
                                    .mass(resultSet.getDouble("mass"))
                                    .hairColor(resultSet.getString("hairColor"))
                                    .skinColor(resultSet.getString("skinColor"))
                                    .eyeColor(resultSet.getString("eyeColor"))
                                    .planet(resultSet.getString("planet"))
                                    .species(resultSet.getString("species"))
                                    .build();

                            characters.add(character);
                        } catch (InvalidStarWarsParameterException e) {
                            throw new IncompatibleVersionException();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error reading all Star Wars characters");
        }

        return characters;
    }

    @Override
    public boolean update(StarWarsCharacter character) throws DataAccessException {
        try (Connection connection = DriverManager.getConnection(url)) {
            String updateQuery = "UPDATE StarWarsCharacters SET gender=?, birthYear=?, height=?, mass=?, hairColor=?, skinColor=?, eyeColor=?, planet=?, species=? WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, character.getGender());
                preparedStatement.setString(2, character.getBirthYear());
                preparedStatement.setInt(3, character.getHeight());
                preparedStatement.setDouble(4, character.getMass());
                preparedStatement.setString(5, character.getHairColor());
                preparedStatement.setString(6, character.getSkinColor());
                preparedStatement.setString(7, character.getEyeColor());
                preparedStatement.setString(8, character.getPlanet());
                preparedStatement.setString(9, character.getSpecies());
                preparedStatement.setString(10, character.getName());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error updating Star Wars character");
        }
    }

    @Override
    public boolean delete(StarWarsCharacter character) throws DataAccessException, DataIntegrityException {
        boolean deleted = false;
        try (Connection connection = DriverManager.getConnection(url)) {
            String deleteQuery = "DELETE FROM StarWarsCharacters WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, character.getName());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected != 0) {
                    deleted = true;
                }

                return deleted;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting Star Wars character");
        }
    }

    public void createTable(){
        try (Connection connection = DriverManager.getConnection(url)) {
            String drop = "DROP TABLE IF EXISTS StarWarsCharacters;";
            String create = "CREATE TABLE IF NOT EXISTS StarWarsCharacters (name VARCHAR(50) PRIMARY KEY," +
                    "    gender VARCHAR(50)," +
                    "    birthYear VARCHAR(50)," +
                    "    height INT," +
                    "    mass DOUBLE," +
                    "    hairColor VARCHAR(50)," +
                    "    skinColor VARCHAR(50)," +
                    "    eyeColor VARCHAR(50)," +
                    "    planet VARCHAR(50)," +
                    "    species VARCHAR(50));";
            try (Statement st1 = connection.createStatement(); Statement st2 = connection.createStatement()) {
                st1.executeUpdate(drop);
                st2.executeUpdate(create);
            }
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        StarWarsCharactersDaoDb starWarsCharactersDaoDb = new StarWarsCharactersDaoDb();
        starWarsCharactersDaoDb.createTable();
    }
}
