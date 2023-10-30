package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StarWarsCharactersDAO_CSV implements StarWarsCharactersDAO {
    private Path filePath;

    public StarWarsCharactersDAO_CSV(Path filePath) {
        setFilePath(filePath);
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        if (filePath == null || !Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new InvalidPathException(filePath.toString(), "Invalid file path");
        }
        this.filePath = filePath;
    }

    @Override
    public void create(StarWarsCharacter character) {
        String characterString = characterToCSVLine(character);
        try (BufferedWriter bw = Files.newBufferedWriter(getFilePath(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            bw.write(characterString);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StarWarsCharacter read(String name) {
        List<StarWarsCharacter> characters = readAll();
        for (StarWarsCharacter character : characters) {
            if (character.getName().trim().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }
        return null;
    }

    @Override
    public List<StarWarsCharacter> readAll() {
        List<StarWarsCharacter> characters = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getFilePath());
            for (String line : lines) {
                String[] data = line.split(",");
                characters.add(parseCharacter(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public boolean update(StarWarsCharacter character) {
        boolean isUpdated = false;
        List<StarWarsCharacter> characters = readAll();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getName().trim().equalsIgnoreCase(character.getName().trim())) {
                characters.set(i, character);
                isUpdated = true;
                break;
            }
        }
        writeAll(characters);
        return isUpdated;
    }

    @Override
    public boolean delete(StarWarsCharacter character) {
        List<StarWarsCharacter> characters = readAll();
        boolean isDeleted = characters.removeIf(c -> c.getName().trim().equalsIgnoreCase(character.getName().trim()));
        writeAll(characters);
        return isDeleted;
    }

    private String characterToCSVLine(StarWarsCharacter character) {
        return character.getName() + "," + character.getGender() + "," + character.getBirthYear() + "," +
                character.getHeight() + "," + character.getMass() + "," + character.getHairColor() + "," +
                character.getSkinColor() + "," + character.getEyeColor() + "," + character.getPlanet() + "," +
                character.getSpecies();
    }

    private StarWarsCharacter parseCharacter(String[] data) {
        return new StarWarsCharacter(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]),
                data[5], data[6], data[7], data[8], data[9]);
    }

    private void writeAll(List<StarWarsCharacter> characters) {
        try {
            List<String> lines = new ArrayList<>();
            for (StarWarsCharacter character : characters) {
                lines.add(characterToCSVLine(character));
            }
            Files.write(getFilePath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

