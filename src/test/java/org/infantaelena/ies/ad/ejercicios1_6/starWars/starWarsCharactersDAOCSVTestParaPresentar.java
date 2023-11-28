package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharacter;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharactersDAOCSV;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class starWarsCharactersDAOCSVTestParaPresentar {

    private StarWarsCharactersDAOCSV dao;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws IOException, NullPathException {
        tempFilePath = Files.createTempFile("test", ".csv");
        dao = new StarWarsCharactersDAOCSV(tempFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    void create() throws InvalidStarWarsParameterException, StarWarsCannotCreateException, StarWarsDuplicateCharacterException, StarWarsCannotReadException {
        StarWarsCharacter character = new StarWarsCharacter.Builder()
                    .name("Luke Skywalker")
                    .gender("male")
                    .birthYear("19BBY")
                    .height(172)
                    .mass(77)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();

        dao.create(character);
        StarWarsCharacter readCharacter = dao.read("Luke Skywalker");
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
        assertEquals("Tatooine", readCharacter.getPlanet());
        assertThrows(StarWarsDuplicateCharacterException.class, () -> dao.create(character));
    }

    @Test
    void read() throws StarWarsCannotReadException, InvalidStarWarsParameterException, StarWarsCannotCreateException, StarWarsDuplicateCharacterException {
        StarWarsCharacter readCharacter = dao.read("Luke Skywalker");
        assertNull(readCharacter);
        StarWarsCharacter character = new StarWarsCharacter.Builder()
                    .name("Luke Skywalker")
                    .gender("male")
                    .birthYear("19BBY")
                    .height(172)
                    .mass(77)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();
        dao.create(character);
        readCharacter = dao.read("Luke Skywalker");
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
    }

    @Test
    void readAll() throws StarWarsCannotReadException, InvalidStarWarsParameterException, StarWarsCannotCreateException, StarWarsDuplicateCharacterException {
        List<StarWarsCharacter> characters = dao.readAll();
        assertNotNull(characters);
        assertTrue(characters.isEmpty());

        StarWarsCharacter character1 = new StarWarsCharacter.Builder()
                    .name("Luke Skywalker")
                    .gender("male")
                    .birthYear("19BBY")
                    .height(172)
                    .mass(77)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();

        StarWarsCharacter character2 = new StarWarsCharacter.Builder()
                    .name("Anakin Skywalker")
                    .gender("male")
                    .birthYear("41.9BBY")
                    .height(188)
                    .mass(84)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();
        dao.create(character1);
        dao.create(character2);
        characters = dao.readAll();
        assertNotNull(characters);
        assertEquals(2, characters.size());
        assertEquals("Luke Skywalker", characters.get(0).getName());
        assertEquals("Anakin Skywalker", characters.get(1).getName());
    }

    @Test
    void update() throws InvalidStarWarsParameterException, StarWarsCannotUpdateException, StarWarsCannotCreateException, StarWarsDuplicateCharacterException, StarWarsCannotReadException {
        StarWarsCharacter character = new StarWarsCharacter.Builder()
                    .name("Anakin Skywalker")
                    .gender("male")
                    .birthYear("41.9BBY")
                    .height(188)
                    .mass(84)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();
        assertFalse(dao.update(character));
        dao.create(character);
        StarWarsCharacter updatedCharacter = new StarWarsCharacter.Builder()
                    .name("Anakin Skywalker")
                    .gender("male")
                    .birthYear("41.9BBY")
                    .height(187)
                    .mass(85)
                    .hairColor("black")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();

        assertTrue(dao.update(updatedCharacter));
        StarWarsCharacter readCharacter = null;
        readCharacter = dao.read("Anakin Skywalker");
        assertNotNull(readCharacter);
        assertEquals("black", readCharacter.getHairColor());
    }

    @Test
    void delete() throws InvalidStarWarsParameterException, StarWarsCannotDeleteException, StarWarsCannotCreateException, StarWarsDuplicateCharacterException, StarWarsCannotReadException {
        StarWarsCharacter character = new StarWarsCharacter.Builder()
                    .name("Anakin Skywalker")
                    .gender("male")
                    .birthYear("41.9BBY")
                    .height(188)
                    .mass(84)
                    .hairColor("blond")
                    .skinColor("fair")
                    .eyeColor("blue")
                    .planet("Tatooine")
                    .species("Human")
                    .build();
        assertFalse(dao.delete(character));
        dao.create(character);
        assertTrue(dao.delete(character));
        assertNull(dao.read("Anakin Skywalker"));
    }
}
