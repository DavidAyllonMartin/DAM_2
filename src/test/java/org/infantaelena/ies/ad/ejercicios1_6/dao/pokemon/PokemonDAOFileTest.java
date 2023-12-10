package org.infantaelena.ies.ad.ejercicios1_6.dao.pokemon;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.*;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.Pokemon;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.PokemonDAOFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDAOFileTest {
    private Path path;

    @BeforeEach
    void setUp() throws IOException {
        this.path = Files.createTempFile("temp_almacen", ".tmp");
    }

    @Test
    void estaVacio() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        boolean isEmpty = false;
        try {
            isEmpty = pokemonDAOFile.estaVacio();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        assertTrue(isEmpty);
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataDestFullException | DataAccessException | DuplicateKeyException e) {
            throw new RuntimeException(e);
        }
        try {
            isEmpty = pokemonDAOFile.estaVacio();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        assertFalse(isEmpty);
    }

    @Test
    void estaLLeno() {
    }

    @Test
    void aniadir() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataDestFullException | DataAccessException | DuplicateKeyException e) {
            throw new RuntimeException(e);
        }
        List<Pokemon> pokemons = null;
        try {
            pokemons = pokemonDAOFile.leerPokemons();
        } catch (DataAccessException | IncompatibleVersionException e) {
            throw new RuntimeException(e);
        }
        try {
            assertFalse(pokemonDAOFile.estaVacio());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
        assertThrows(DuplicateKeyException.class, () -> pokemonDAOFile.aniadir(pokemon));
    }

    @Test
    void eliminar() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            assertFalse(pokemonDAOFile.eliminar(pokemon));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataDestFullException | DataAccessException | DuplicateKeyException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(pokemonDAOFile.eliminar(pokemon));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void pokemonCSV() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        //Añadimos dos registros para comprobar que la función de añadir al final del archivo funciona
        pokemonDAOFile.pokemonCSV(this.path.toString(),"Pikachu", 5, 35, 55, 40, 50, 50, 90);
        pokemonDAOFile.pokemonCSV(this.path.toString(),"Bulbasaur", 100, 35, 55, 40, 50, 50, 90);

        List<String> registros = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pokemonDAOFile.getAlmacen())){
            String line;
            while ((line = br.readLine()) != null){
                registros.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String pikachuCSV = "Pikachu;5;35;55;40;50;50;90";
        String bulbasaurCSV = "Bulbasaur;100;35;55;40;50;50;90";

        assertEquals(registros.get(0), pikachuCSV);
        assertEquals(registros.get(1), bulbasaurCSV);
    }

    @Test
    void imprimirPokemonCSV() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        assertThrows(IllegalArgumentException.class, () -> pokemonDAOFile.imprimirPokemonCSV(null));
        assertThrows(IllegalArgumentException.class, () -> pokemonDAOFile.imprimirPokemonCSV("src"));
        assertThrows(RuntimeException.class, () -> pokemonDAOFile.imprimirPokemonCSV("invalid.csv"));
        assertDoesNotThrow(() -> pokemonDAOFile.imprimirPokemonCSV(this.path.toString()));
    }


    @Test
    void imprimirPokemon() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);

        // Añadimos un Pokémon para probar la impresión
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataDestFullException | DataAccessException | DuplicateKeyException e) {
            throw new RuntimeException(e);
        }

        String expectedOutput = "Nombre:Pikachu\nNivel:5\nHP:35\nAtaque:55\nDefensa:40\nAtaque Especial:50\nDefensa Especial:50\nVelocidad:90\n";
        try {
            assertEquals(expectedOutput, pokemonDAOFile.leerPokemons("Pikachu").get(0).toString());
        } catch (DataAccessException | IncompatibleVersionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void leerPokemons() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);

        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataDestFullException | DuplicateKeyException | DataAccessException e) {
            throw new RuntimeException(e);
        }

        List<Pokemon> pokemons = null;
        try {
            pokemons = pokemonDAOFile.leerPokemons();
        } catch (DataAccessException | IncompatibleVersionException e) {
            throw new RuntimeException(e);
        }
        assertFalse(pokemons.isEmpty());
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));

        Pokemon pokemon2 = new Pokemon("Bulbasaur", 100, 35, 55, 40, 50, 50, 90);

        try {
            pokemonDAOFile.aniadir(pokemon2);
        } catch (DataAccessException | DataDestFullException | DuplicateKeyException e) {
            throw new RuntimeException(e);
        }


        try {
            pokemons = pokemonDAOFile.leerPokemons();
        } catch (DataAccessException | IncompatibleVersionException e) {
            throw new RuntimeException(e);
        }
        assertFalse(pokemons.isEmpty());
        assertEquals(2, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
        assertEquals(pokemon2, pokemons.get(1));
    }

    @Test
    void LeerPokemonsConString() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);

        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (DataAccessException | DataDestFullException | DuplicateKeyException e) {
            // No van a saltar
        }

        List<Pokemon> pokemons = null;
        try {
            pokemons = pokemonDAOFile.leerPokemons("achu");
        } catch (DataAccessException | IncompatibleVersionException e) {
            throw new RuntimeException(e);
        }
        assertFalse(pokemons.isEmpty());
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
    }
}