package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_4;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.*;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDaoDb implements PokemonDAO {

    private String url = "jdbc:mysql://192.168.56.101:3306/pokemon_db";
    private String user = "admin00";
    private String password = "alumno";
    @Override
    public boolean estaVacio() throws DataAccessException {

        boolean estaVacio = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String select = "SELECT idNum FROM pokemon";
            try (PreparedStatement ps = connection.prepareStatement(select)){
                try (ResultSet rs = ps.executeQuery()){
                    if (!rs.next()){
                        estaVacio = true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de comunicación con la base de datos");
        }
        return estaVacio;
    }

    @Override
    public boolean estaLLeno(){
        return false;
    }

    @Override
    public void aniadir(Pokemon pokemon) throws DataAccessException, DuplicateKeyException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String insertPokemon = "INSERT INTO pokemon (name, HP, speed, attack, special_attack, defense, special_defense, evolve_id, generation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String insertType = "INSERT INTO pokemon_type (pokemon_id, type1, type2) VALUES (?, ?, ?)";
            String selectId = "SELECT idNum FROM pokemon WHERE name = ?";

            try (PreparedStatement pokemonPs = connection.prepareStatement(insertPokemon);
                 PreparedStatement typePs = connection.prepareStatement(insertType);
                 PreparedStatement pokemonId = connection.prepareStatement(selectId)) {

                connection.setAutoCommit(false);

                pokemonPs.setString(1, pokemon.getName());
                pokemonPs.setInt(2, pokemon.getHp());
                pokemonPs.setInt(3, pokemon.getSpeed());
                pokemonPs.setInt(4, pokemon.getAttack());
                pokemonPs.setInt(5, pokemon.getSpecialAttack());
                pokemonPs.setInt(6, pokemon.getDefense());
                pokemonPs.setInt(7, pokemon.getSpecialDefense());
                pokemonPs.setInt(8, pokemon.getEvolveId());
                pokemonPs.setInt(9, pokemon.getGeneration());
                pokemonPs.executeUpdate();

                pokemonId.setString(1, pokemon.getName());

                try (ResultSet rs = pokemonId.executeQuery()) {
                    if (rs.next()) {
                        typePs.setInt(1, rs.getInt("idNum"));
                        typePs.setString(2, pokemon.getType1());
                        typePs.setString(3, pokemon.getType2());
                        typePs.executeUpdate();
                    }
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                // Discernir si el error es por clave duplicada
                if (e.getSQLState().startsWith("23")) {
                    throw new DuplicateKeyException("Ya existe un Pokemon con el mismo nombre en la base de datos");
                } else {
                    throw new DataAccessException("Error de comunicación con la base de datos");
                }
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de conexión con la base de datos");
        }
    }

    @Override
    public boolean eliminar(Pokemon pokemon) throws DataAccessException, DataIntegrityException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String deletePokemon = "DELETE FROM pokemon WHERE name = ?";
            String deleteType = "DELETE FROM pokemon_type WHERE pokemon_id = (SELECT idNum FROM pokemon WHERE name = ?)";

            try (PreparedStatement deleteTypePs = connection.prepareStatement(deleteType);
                 PreparedStatement deletePokemonPs = connection.prepareStatement(deletePokemon)) {

                connection.setAutoCommit(false);

                deleteTypePs.setString(1, pokemon.getName());
                int typeRowsAffected = deleteTypePs.executeUpdate();

                deletePokemonPs.setString(1, pokemon.getName());
                int pokemonRowsAffected = deletePokemonPs.executeUpdate();

                if (typeRowsAffected == 0 || pokemonRowsAffected == 0) {
                    connection.rollback();
                    throw new DataIntegrityException("No se pudo eliminar el Pokemon debido a restricciones de integridad");
                }

                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Error de comunicación con la base de datos");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de conexión con la base de datos");
        }
    }

    @Override
    public List<Pokemon> leerPokemons() throws DataAccessException {
        List<Pokemon> pokemonList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectPokemons = "SELECT * FROM pokemon";
            String selectTypes = "SELECT * FROM pokemon_type WHERE pokemon_id = ?";

            try (PreparedStatement selectPokemonsPs = connection.prepareStatement(selectPokemons);
                 PreparedStatement selectTypesPs = connection.prepareStatement(selectTypes)) {

                buildPokemon(pokemonList, selectPokemonsPs, selectTypesPs);

            } catch (SQLException e) {
                throw new DataAccessException("Error de comunicación con la base de datos");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de conexión con la base de datos");
        }

        return pokemonList;
    }

    @Override
    public List<Pokemon> leerPokemons(String nombre) throws DataAccessException {
        List<Pokemon> pokemonList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectPokemons = "SELECT * FROM pokemon WHERE name LIKE ?";
            String selectTypes = "SELECT * FROM pokemon_type WHERE pokemon_id = ?";

            try (PreparedStatement selectPokemonsPs = connection.prepareStatement(selectPokemons);
                 PreparedStatement selectTypesPs = connection.prepareStatement(selectTypes)) {

                selectPokemonsPs.setString(1, "%" + nombre + "%");

                buildPokemon(pokemonList, selectPokemonsPs, selectTypesPs);

            } catch (SQLException e) {
                throw new DataAccessException("Error de comunicación con la base de datos al leer los Pokemons");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de conexión con la base de datos al leer los Pokemons");
        }

        return pokemonList;
    }

    private void buildPokemon(List<Pokemon> pokemonList, PreparedStatement selectPokemonsPs, PreparedStatement selectTypesPs) throws SQLException {
        try (ResultSet pokemonsRs = selectPokemonsPs.executeQuery()) {
            while (pokemonsRs.next()) {
                Pokemon pokemon = new Pokemon.Builder()
                        .name(pokemonsRs.getString("name"))
                        .hp(pokemonsRs.getInt("HP"))
                        .speed(pokemonsRs.getInt("speed"))
                        .attack(pokemonsRs.getInt("attack"))
                        .specialAttack(pokemonsRs.getInt("special_attack"))
                        .defense(pokemonsRs.getInt("defense"))
                        .specialDefense(pokemonsRs.getInt("special_defense"))
                        .evolveId(pokemonsRs.getInt("evolve_id"))
                        .generation(pokemonsRs.getInt("generation"))
                        .build();

                // Obtener los tipos del Pokemon
                selectTypesPs.setInt(1, pokemonsRs.getInt("idNum"));
                try (ResultSet typesRs = selectTypesPs.executeQuery()) {
                    if (typesRs.next()) {
                        pokemon.setType1(typesRs.getString("type1"));
                        pokemon.setType2(typesRs.getString("type2"));
                    }
                }

                pokemonList.add(pokemon);
            }
        }
    }

    @Override
    public void actualizar(Pokemon pokemon) throws DataAccessException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updatePokemon = "UPDATE pokemon SET HP=?, speed=?, attack=?, special_attack=?, defense=?, special_defense=?, evolve_id=?, generation=? WHERE name=?";
            String updateType = "UPDATE pokemon_type SET type1=?, type2=? WHERE pokemon_id = ?";

            try (PreparedStatement updatePokemonPs = connection.prepareStatement(updatePokemon);
                 PreparedStatement updateTypePs = connection.prepareStatement(updateType)) {

                connection.setAutoCommit(false);

                updatePokemonPs.setInt(1, pokemon.getHp());
                updatePokemonPs.setInt(2, pokemon.getSpeed());
                updatePokemonPs.setInt(3, pokemon.getAttack());
                updatePokemonPs.setInt(4, pokemon.getSpecialAttack());
                updatePokemonPs.setInt(5, pokemon.getDefense());
                updatePokemonPs.setInt(6, pokemon.getSpecialDefense());
                updatePokemonPs.setInt(7, pokemon.getEvolveId());
                updatePokemonPs.setInt(8, pokemon.getGeneration());
                updatePokemonPs.setString(9, pokemon.getName());

                updatePokemonPs.executeUpdate();

                // Actualizar tipos
                updateTypePs.setString(1, pokemon.getType1());
                updateTypePs.setString(2, pokemon.getType2());
                updateTypePs.setInt(3, obtenerIdPokemon(connection, pokemon.getName()));

                updateTypePs.executeUpdate();

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Error de comunicación con la base de datos");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error de conexión con la base de datos");
        }
    }

    private int obtenerIdPokemon(Connection connection, String nombre) throws SQLException {
        String selectId = "SELECT idNum FROM pokemon WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(selectId)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idNum");
                } else {
                    throw new SQLException("No se encontró un Pokémon con el nombre proporcionado");
                }
            }
        }
    }

    @Override
    public void pokemonCSV(String ruta, String name, int level, int life, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            String pokemonInfo = String.format("%s;%d;%d;%d;%d;%d;%d;%d\n", name, level, life, attack, defense, specialAttack, specialDefense, speed);
            writer.write(pokemonInfo);

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
    @Override
    public void imprimirPokemonCSV(String ruta) throws NoSuchFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 8) {
                    imprimirInformacionPokemon(data);
                } else {
                    System.out.println("La línea no tiene el formato correcto: " + line);
                }
            }
        } catch (IOException e) {
            throw new NoSuchFileException("No existe el fichero: " + ruta);
        }
    }

    private void imprimirInformacionPokemon(String[] data) {
        System.out.println("Name: " + data[0]);
        System.out.println("level: " + Integer.parseInt(data[1]));
        System.out.println("HP: " + Integer.parseInt(data[2]));
        System.out.println("attack: " + Integer.parseInt(data[3]));
        System.out.println("defense: " + Integer.parseInt(data[4]));
        System.out.println("Special attack: " + Integer.parseInt(data[5]));
        System.out.println("Special defense: " + Integer.parseInt(data[6]));
        System.out.println("speed: " + Integer.parseInt(data[7]));
        System.out.println();
    }

    @Override
    public void imprimirPokemon(String nombre) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectPokemon = "SELECT * FROM pokemon WHERE name LIKE ?";
            try (PreparedStatement ps = connection.prepareStatement(selectPokemon)) {
                ps.setString(1, "%" + nombre + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        imprimirInformacionPokemon(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al imprimir Pokemon: " + e.getMessage());
        }
    }

    private void imprimirInformacionPokemon(ResultSet rs) throws SQLException {
        System.out.println("Name: " + rs.getString("name"));
        //System.out.println("level: " + ); La base de datos no tiene un atributo level
        System.out.println("HP: " + rs.getInt("HP"));
        System.out.println("attack: " + rs.getInt("attack"));
        System.out.println("defense: " + rs.getInt("defense"));
        System.out.println("Special attack: " + rs.getInt("special_attack"));
        System.out.println("Special defense: " + rs.getInt("special_defense"));
        System.out.println("speed: " + rs.getInt("speed"));
        System.out.println();
    }
}
