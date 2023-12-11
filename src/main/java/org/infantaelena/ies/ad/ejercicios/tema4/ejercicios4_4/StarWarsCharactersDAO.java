package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_4;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.StarWarsCharacter;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.*;

import java.util.List;

public interface StarWarsCharactersDAO {
    void create(StarWarsCharacter character) throws DataAccessException, DuplicateKeyException;

    StarWarsCharacter read(String name) throws DataAccessException, IncompatibleVersionException;

    List<StarWarsCharacter> readAll() throws DataAccessException, IncompatibleVersionException;

    boolean update(StarWarsCharacter character) throws DataAccessException;

    boolean delete(StarWarsCharacter character) throws DataAccessException, DataIntegrityException;
}

