/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.*;

import java.nio.file.NoSuchFileException;
import java.util.List;

public interface PokemonDAO {


	/**
	 * Comprueba si el almacén está vacio.
	 *
	 * @return true si está vacio.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 */
	public boolean estaVacio() throws DataAccessException;

	/**
	 * Comprueba si el almacén está lleno.
	 *
	 * @return true si está lleno.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 */
	public boolean estaLLeno() throws DataAccessException;

	/**
	 * Añade un nuevo elemento al almacén si hay sitio y no está ya.
	 *
	 * @param pokemon a añadir al almacén.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 * @throws DataDestFullException si el pokemon no se pudo escribir.
	 * @throws DuplicateKeyException si el pokemon ya existía en el almacén.
	 */
	public void aniadir(Pokemon pokemon) throws DataAccessException, DataDestFullException,
			DuplicateKeyException;

	/**
	 * Elimina un elemento del almacén si está en él.
	 *
	 * @param pokemon
	 * @return true si elimina el elemento, false en caso contrario.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 * @throws DataIntegrityException si la eliminación no se puede realizar por violar restricciones de integridad.
	 */
	public boolean eliminar(Pokemon pokemon) throws DataAccessException, DataIntegrityException;

	/**
	 * Devuelve una lista de objetos pokemon del almacén.
	 *
	 * @return lista con todos los pokemons que haya almacenados.
	 * Lista vacía si no hay ninguno.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 * @throws IncompatibleVersionException si los datos leídos no se corresponden con la clase Pokemon.
	 */
	public List<Pokemon> leerPokemons() throws DataAccessException, IncompatibleVersionException;

	/**
	 * Devuelve una lista de objetos pokemon que contengan esa cadena en el
	 * nombre.
	 * <p>
	 * Por ejemplo “saur” debería de devolver a Bulbasaur, Ivysaur,
	 * Venusaur y Mega Venusaur.
	 *
	 * @param nombre texto que debe contener el nombre.
	 * @return lista con todos los pokemons que haya almacenados que cumplan el criterio.
	 * Lista vacía si no hay ninguno.
	 *
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 * @throws IncompatibleVersionException si los datos leídos no se corresponden con la clase Pokemon.
	 */
	public List<Pokemon> leerPokemons(String nombre) throws DataAccessException, IncompatibleVersionException;

	/**
	 * Actualiza el Pokemon con los datos nuevos. Se considera que el pokemon es el mismo, si el método
	 * equals de la clase pokemon devuelve true.
	 *
	 * @param p el Pokemon con los mismos datos.
	 * @throws DataAccessException si no se ha podido acceder al almacén de datos.
	 * @throws IncompatibleVersionException si la estructura del pokemon recibido como argumento no se corresponde
	 *      con la que hay en el almacén.
	 */
	public void actualizar(Pokemon p)  throws DataAccessException, IncompatibleVersionException;

	/**
	 * Escribe en un fichero de texto “csv" la información de un pokemon
	 * separando los campos por puntos y coma.
	 * Nombre;nivel;Vida;ataque;defensa;ataqueEspecial;DefensaEspecial;velocidad
	 * <p>
	 * En caso de existir el fichero, no se borrará, se seguirán añadiendo
	 * pokemons al final.
	 *
	 * @param ruta fichero de texto.
	 * @param name Nombre.
	 * @param level Nivel.
	 * @param life Vida.
	 * @param attack Ataque.
	 * @param defense Defensa.
	 * @param specialAttack Ataque Especial.
	 * @param specialdefense Defensa Especial.
	 * @param speed Velocidad.
	 */
	public void pokemonCSV(String ruta, String name, int level, int life, int attack, int defense, int specialAttack, int specialdefense, int speed);


	/**
	 * Imprime por pantalla el contenido del fichero csv con los pokemon de la forma:
	 * Name: <Nombre>
	 * level: <nivel>
	 * HP: <Vida>
	 * attack: <Ataque>
	 * defense: <defensa>
	 * Special attack: <AtaqueEspecial>
	 * Special defense: <DefensaEspecial>
	 * speed: <velocidad>
	 *
	 * @param ruta fichero de texto.
	 * @throws java.nio.file.NoSuchFileException si no existe el fichero.
	 */
	public void imprimirPokemonCSV(String ruta) throws NoSuchFileException;

	/**
	 * Imprime por pantalla los objetos pokemon del almacén que contengan
	 * esa cadena en el nombre.
	 * <p>
	 * Por ejemplo “saur” debería de devolver a Bulbasaur, Ivysaur,
	 * Venusaur y Mega Venusaur.
	 * Name: <Nombre>
	 * level: <nivel>
	 * HP: <Vida>
	 * attack: <Vida>
	 * defense: <defensa>
	 * Special attack: <AtaqueEspecial>
	 * Special defense: <DefensaEspecial>
	 * speed: <velocidad>
	 *
	 * @param nombre texto que debe contener el nombre.
	 */
	public void imprimirPokemon(String nombre);
}

