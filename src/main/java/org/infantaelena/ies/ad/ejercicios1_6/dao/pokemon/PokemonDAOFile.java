package org.infantaelena.ies.ad.ejercicios1_6.dao.pokemon;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author David Ayllón
 */
public class PokemonDAOFile implements PokemonDAO {
    //Atributos
    private Path almacen;
    public PokemonDAOFile(Path almacen) throws IllegalArgumentException{
        setAlmacen(almacen);
    }
    //Getters y setters
    public Path getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Path almacen) throws IllegalArgumentException{

        if (almacen == null || Files.isDirectory(almacen)){
            throw new IllegalArgumentException("La ruta tiene que referirse a un archivo");
        }else{
            if (!Files.exists(almacen)){
                try {
                    Files.createFile(almacen);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            this.almacen = almacen;
        }
    }

    @Override
    public boolean estaVacio() {
        boolean estaVacio;
        List<Pokemon> pokemons = leerPokemons();
        estaVacio = pokemons.isEmpty();
        return estaVacio;
    }

    @Override
    public boolean estaLLeno() {
        boolean estaLleno = false;
        return estaLleno;
    }

    @Override
    public void aniadir(Pokemon pokemon) throws NoMasPokemonsException, PokemonDuplicadoException {
        if (estaLLeno()){
            throw new NoMasPokemonsException();
        }
        List<Pokemon> pokemons = leerPokemons();
        for (Pokemon pkmn : pokemons){
            if (pkmn.equals(pokemon)){
                throw new PokemonDuplicadoException();
            }
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(getAlmacen()))){
            oos.writeObject(pokemon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCSVLine(Pokemon pokemon) {
        String name = pokemon.getName();
        String level = String.valueOf(pokemon.getLevel());
        String health = String.valueOf(pokemon.getHealth());
        String attack = String.valueOf(pokemon.getAttack());
        String defense = String.valueOf(pokemon.getDefense());
        String specialAttack = String.valueOf(pokemon.getSpecialAttack());
        String specialDefense = String.valueOf(pokemon.getSpecialDefense());
        String speed = String.valueOf(pokemon.getSpeed());

        StringJoiner joiner = new StringJoiner(";");
        joiner.add(name)
                .add(level)
                .add(health)
                .add(attack)
                .add(defense)
                .add(specialAttack)
                .add(specialDefense)
                .add(speed);
        return joiner.toString();
    }

    @Override
    public boolean eliminar(Pokemon pokemon) {
        List<Pokemon> pokemons = leerPokemons();
        boolean removed = pokemons.remove(pokemon);
        if (removed){
            try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(getAlmacen()))){
                for (Pokemon pkmn : pokemons){
                    oos.writeObject(pkmn);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return removed;
    }

    @Override
    public void pokemonCSV(String ruta, String name, int level, int life, int attack, int defense, int specialAttack, int specialdefense, int speed) throws IllegalArgumentException{
        Path path = checkPathFile(ruta);
        if (path != null){
            try(BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                Pokemon pokemon = new Pokemon(name, level, life, attack, defense, specialAttack, specialdefense, speed);
                String csvLine = getCSVLine(pokemon);
                bw.write(csvLine);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new IllegalArgumentException("La ruta tiene que referirse a un archivo");
        }
    }

    @Override
    public void imprimirPokemonCSV(String ruta) throws IllegalArgumentException {
        Path path = checkPathFile(ruta);
        if (path != null){
            try(BufferedReader br = Files.newBufferedReader(path)){
                String line;
                while ((line = br.readLine()) != null){
                    String[] atributos = line.split(";");
                    Pokemon pokemon = new Pokemon(atributos);
                    System.out.println(pokemon);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new IllegalArgumentException("La ruta tiene que referirse a un archivo");
        }
    }

    @Override
    public void imprimirPokemon(String nombre) {
        List<Pokemon> pokemons = leerPokemons(nombre);
        for (Pokemon pokemon : pokemons){
            System.out.println(pokemon);
        }
    }

    @Override
    public List<Pokemon> leerPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(getAlmacen()))) {
            while (true) {
                Pokemon pokemon = (Pokemon) ois.readObject();
                pokemons.add(pokemon);
            }
        } catch (EOFException e){
            //End of file reached
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pokemons;
    }

    @Override
    public List<Pokemon> leerPokemons(String nombre) {
        List<Pokemon> pokemons = new ArrayList<>();
        nombre = nombre.trim().toLowerCase();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(getAlmacen()))) {
            while (true) {
                Pokemon pokemon = (Pokemon) ois.readObject();
                String name = pokemon.getName().trim().toLowerCase();
                if (name.contains(nombre)){
                    pokemons.add(pokemon);
                }
            }
        } catch (EOFException e){
            //End of file reached
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pokemons;
    }

    private static Path checkPathFile(String path){
        if (path == null || path.isEmpty()){
            return null;
        }
        Path p;
        try {
            p = Paths.get(path);
            if (Files.isDirectory(p)){
                p = null;
            }
        }catch (InvalidPathException e){
            p = null;
        }
        return p;
    }

    public static void main(String[] args) {
        Path path = Paths.get("src/main/resources/ad/ejercicios1_6/almacen.csv");
        PokemonDAOFile pokemonDAOFile = new PokemonDAOFile(path);

        Pokemon[] pokemons = new Pokemon[10];
        pokemons[0] = new Pokemon("Bulbasaur", 5, 45, 49, 49, 65, 65, 45);
        pokemons[1] = new Pokemon("Ivysaur", 5, 60, 62, 63, 80, 80, 60);
        pokemons[2] = new Pokemon("Venusaur", 5, 80, 82, 83, 100, 100, 80);
        pokemons[3] = new Pokemon("Charmander", 5, 39, 52, 43, 60, 50, 65);
        pokemons[4] = new Pokemon("Charmeleon", 5, 58, 64, 58, 80, 65, 80);
        pokemons[5] = new Pokemon("Charizard", 5, 78, 84, 78, 109, 85, 100);
        pokemons[6] = new Pokemon("Squirtle", 5, 44, 48, 65, 50, 64, 43);
        pokemons[7] = new Pokemon("Wartortle", 5, 59, 63, 80, 65, 80, 58);
        pokemons[8] = new Pokemon("Blastoise", 5, 79, 83, 100, 85, 105, 78);
        pokemons[9] = new Pokemon("Pikachu", 5, 35, 55, 40, 50, 50, 90);
        for (Pokemon pokemon : pokemons) {
            try {
                pokemonDAOFile.aniadir(pokemon);
            } catch (NoMasPokemonsException e) {
                throw new RuntimeException(e);
            } catch (PokemonDuplicadoException e) {
                throw new RuntimeException(e);
            }
        }
    }
}