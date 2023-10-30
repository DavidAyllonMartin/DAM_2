package org.infantaelena.ies.ad.ejercicios1_6.dao.pokemon;

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
        boolean isEmpty = pokemonDAOFile.estaVacio();
        assertTrue(isEmpty);
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (NoMasPokemonsException | PokemonDuplicadoException e) {
            //No van a saltar
        }
        isEmpty = pokemonDAOFile.estaVacio();
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
        } catch (NoMasPokemonsException | PokemonDuplicadoException e) {
            //No van a saltar
        }
        List<Pokemon> pokemons = pokemonDAOFile.leerPokemons();
        assertFalse(pokemonDAOFile.estaVacio());
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
        assertThrows(PokemonDuplicadoException.class, () -> pokemonDAOFile.aniadir(pokemon));
    }

    @Test
    void eliminar() {
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);
        Pokemon pokemon = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        assertFalse(pokemonDAOFile.eliminar(pokemon));
        try {
            pokemonDAOFile.aniadir(pokemon);
        } catch (NoMasPokemonsException | PokemonDuplicadoException e) {
            //No van a saltar
        }
        assertTrue(pokemonDAOFile.eliminar(pokemon));
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
    }

    @Test
    void leerPokemons() {
    }

    @Test
    void testLeerPokemons() {
    }
}