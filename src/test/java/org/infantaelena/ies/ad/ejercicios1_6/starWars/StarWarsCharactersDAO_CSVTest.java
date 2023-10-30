package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StarWarsCharactersDAO_CSVTest {

    private StarWarsCharactersDAO_CSV dao;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws IOException {
        tempFilePath = Files.createTempFile("test", ".csv");
        dao = new StarWarsCharactersDAO_CSV(tempFilePath);
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
    }

    @Test
    void read() {
        StarWarsCharacter retrievedCharacter = dao.read("TestName");
        assertNull(retrievedCharacter);
    }

    @Test
    void readAll() {
        List<StarWarsCharacter> characters = dao.readAll();
        assertNotNull(characters);
        assertTrue(characters.isEmpty());
    }

    @Test
    void update() {
        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        assertFalse(dao.update(character));
    }

    @Test
    void delete() {
        StarWarsCharacter character = new StarWarsCharacter("TestName", "male", "50BBY", 180, 75,
                "black", "fair", "blue", "TestPlanet", "Human");
        assertFalse(dao.delete(character));
    }
}
