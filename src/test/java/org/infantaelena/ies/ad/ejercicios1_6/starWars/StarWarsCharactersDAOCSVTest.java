package org.infantaelena.ies.ad.ejercicios1_6.starWars;

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
    void setUp() throws IOException {
        tempFilePath = Files.createTempFile("test", ".csv");
        dao = new StarWarsCharactersDAOCSV(tempFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    void create() {
        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        dao.create(character);
        StarWarsCharacter retrievedCharacter = dao.read("TestName");
        assertNotNull(retrievedCharacter);
        assertEquals("TestName", retrievedCharacter.getName());
        assertEquals("TestPlanet", retrievedCharacter.getPlanet());

    }

    @Test
    void read() {
        StarWarsCharacter retrievedCharacter = dao.read("TestName");
        assertNull(retrievedCharacter);

        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75, "black", "fair", "blue", "TestPlanet", "Human");
        dao.create(character);
        retrievedCharacter = dao.read("TestName");
        assertNotNull(retrievedCharacter);
        assertEquals("TestName", retrievedCharacter.getName());
    }

    @Test
    void readAll() {
        List<StarWarsCharacter> characters = dao.readAll();
        assertNotNull(characters);
        assertTrue(characters.isEmpty());

        StarWarsCharacter character1 = new StarWarsCharacter("TestName1", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        StarWarsCharacter character2 = new StarWarsCharacter("TestName2", "female", "40BBY", 170, 65,
                "blonde", "fair", "green", "TestPlanet", "Human");
        dao.create(character1);
        dao.create(character2);
        characters = dao.readAll();
        assertNotNull(characters);
        assertEquals(2, characters.size());
        assertEquals("TestName1", characters.get(0).getName());
        assertEquals("TestName2", characters.get(1).getName());
    }

    @Test
    void update() {
        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        assertFalse(dao.update(character));

        dao.create(character);
        StarWarsCharacter updatedCharacter = new StarWarsCharacter("TestName", "female", "40BBY", 170, 65,
                "blonde", "fair", "green", "TestPlanet", "Human");
        assertTrue(dao.update(updatedCharacter));
        StarWarsCharacter retrievedCharacter = dao.read("TestName");
        assertNotNull(retrievedCharacter);
        assertEquals("female", retrievedCharacter.getGender());
    }

    @Test
    void delete() {
        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        assertFalse(dao.delete(character));
        dao.create(character);
        assertTrue(dao.delete(character));
        assertNull(dao.read("TestName"));
    }
}
