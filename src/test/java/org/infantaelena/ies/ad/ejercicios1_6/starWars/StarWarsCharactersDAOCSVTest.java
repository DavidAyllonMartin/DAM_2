package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharacter;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharactersDAOCSV;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StarWarsCharactersDAOCSVTest {

    private StarWarsCharactersDAOCSV dao;
    private Path tempFilePath;

    @BeforeEach
    void setUp() {
        try {
            tempFilePath = Files.createTempFile("test", ".csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dao = new StarWarsCharactersDAOCSV(tempFilePath);
        } catch (IOException | NullPathException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void create() {
        StarWarsCharacter character = null;
        try {
            character = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            dao.create(character);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        StarWarsCharacter readCharacter = null;
        try {
            readCharacter = dao.read("Luke Skywalker");
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
        assertEquals("Tatooine", readCharacter.getPlanet());
        StarWarsCharacter finalCharacter = character;
        assertThrows(StarWarsDuplicateCharacterException.class, () -> dao.create(finalCharacter));
    }

    @Test
    void read() {
        StarWarsCharacter readCharacter = null;
        try {
            readCharacter = dao.read("Luke Skywalker");
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNull(readCharacter);

        StarWarsCharacter character = null;
        try {
            character = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            dao.create(character);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        try {
            readCharacter = dao.read("Luke Skywalker");
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
    }

    @Test
    void readAll() {
        List<StarWarsCharacter> characters = null;
        try {
            characters = dao.readAll();
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(characters);
        assertTrue(characters.isEmpty());

        StarWarsCharacter character1 = null;
        try {
            character1 = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        StarWarsCharacter character2 = null;
        try {
            character2 = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            dao.create(character1);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        try {
            dao.create(character2);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        try {
            characters = dao.readAll();
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(characters);
        assertEquals(2, characters.size());
        assertEquals("Luke Skywalker", characters.get(0).getName());
        assertEquals("Anakin Skywalker", characters.get(1).getName());
    }

    @Test
    void update() {
        StarWarsCharacter character = null;
        try {
            character = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            assertFalse(dao.update(character));
        } catch (StarWarsCannotUpdateException e) {
            throw new RuntimeException(e);
        }

        try {
            dao.create(character);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        StarWarsCharacter updatedCharacter = null;
        try {
            updatedCharacter = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }

        try {
            assertTrue(dao.update(updatedCharacter));
        } catch (StarWarsCannotUpdateException e) {
            throw new RuntimeException(e);
        }
        StarWarsCharacter readCharacter = null;
        try {
            readCharacter = dao.read("Anakin Skywalker");
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(readCharacter);
        assertEquals("black", readCharacter.getHairColor());
    }

    @Test
    void delete() {
        StarWarsCharacter character = null;
        try {
            character = new StarWarsCharacter.Builder()
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
        } catch (InvalidStarWarsParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            assertFalse(dao.delete(character));
        } catch (StarWarsCannotDeleteException e) {
            throw new RuntimeException(e);
        }
        try {
            dao.create(character);
        } catch (StarWarsDuplicateCharacterException | StarWarsCannotCreateException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(dao.delete(character));
        } catch (StarWarsCannotDeleteException e) {
            throw new RuntimeException(e);
        }
        try {
            assertNull(dao.read("Anakin Skywalker"));
        } catch (StarWarsCannotReadException e) {
            throw new RuntimeException(e);
        }
    }
}
