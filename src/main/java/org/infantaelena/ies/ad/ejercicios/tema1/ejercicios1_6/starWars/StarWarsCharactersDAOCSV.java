package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StarWarsCharactersDAOCSV implements StarWarsCharactersDAO {
    private Path filePath;

    public StarWarsCharactersDAOCSV(Path filePath) throws IOException, NullPathException {
        setFilePath(filePath);
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) throws IOException, NullPathException {
        if (filePath == null) {
            throw new NullPathException();
        }

        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new IOException("Failed to create the file", e);
            }
        }
        this.filePath = filePath;
    }

    @Override
    public void create(StarWarsCharacter character) throws StarWarsDuplicateCharacterException, StarWarsCannotCreateException {
        List<StarWarsCharacter> characters = null;
        try {
            characters = readAll();
        } catch (StarWarsCannotReadException e) {
            throw new StarWarsCannotCreateException(e);
        }
        if (characters.contains(character)) {
            throw new StarWarsDuplicateCharacterException();
        }
        String characterString = characterToCSVLine(character);
        try (BufferedWriter bw = Files.newBufferedWriter(getFilePath(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(characterString);
            bw.newLine();
        } catch (IOException e) {
            throw new StarWarsCannotCreateException(e);
        }
    }

    @Override
    public StarWarsCharacter read(String name) throws StarWarsCannotReadException {
        List<StarWarsCharacter> characters = readAll();
        for (StarWarsCharacter character : characters) {
            if (character.getName().trim().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }
        return null;
    }

    @Override
    public List<StarWarsCharacter> readAll() throws StarWarsCannotReadException {
        List<StarWarsCharacter> characters = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getFilePath());
            for (String line : lines) {
                String[] data = line.split(",");
                try {
                    characters.add(parseCharacter(data));
                } catch (NullPointerException e) {
                    throw new StarWarsCannotReadException("Invalid CSV format", e);
                } catch (InvalidStarWarsParameterException e) {
                    throw new StarWarsCannotReadException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public boolean update(StarWarsCharacter character) throws StarWarsCannotUpdateException {
        boolean isUpdated = false;
        List<StarWarsCharacter> characters = null;
        try {
            characters = readAll();
        } catch (StarWarsCannotReadException e) {
            throw new StarWarsCannotUpdateException(e);
        }
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).equals(character)) {
                characters.set(i, character);
                isUpdated = true;
                break;
            }
        }
        writeAll(characters);
        return isUpdated;
    }

    @Override
    public boolean delete(StarWarsCharacter character) throws StarWarsCannotDeleteException {
        List<StarWarsCharacter> characters = null;
        try {
            characters = readAll();
        } catch (StarWarsCannotReadException e) {
            throw new StarWarsCannotDeleteException(e);
        }
        boolean isDeleted = characters.removeIf(c -> c.equals(character));
        writeAll(characters);
        return isDeleted;
    }

    private String characterToCSVLine(StarWarsCharacter character) {
        return character.getName() + "," + character.getGender() + "," + character.getBirthYear() + "," +
                character.getHeight() + "," + character.getMass() + "," + character.getHairColor() + "," +
                character.getSkinColor() + "," + character.getEyeColor() + "," + character.getPlanet() + "," +
                character.getSpecies();
    }

    private StarWarsCharacter parseCharacter(String[] data) throws InvalidStarWarsParameterException, NullPointerException {
        return new StarWarsCharacter(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), data[5], data[6], data[7], data[8], data[9]);
    }

    private void writeAll(List<StarWarsCharacter> characters) {
        try {
            List<String> lines = new ArrayList<>();
            for (StarWarsCharacter character : characters) {
                lines.add(characterToCSVLine(character));
            }
            Files.write(getFilePath(), lines);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}