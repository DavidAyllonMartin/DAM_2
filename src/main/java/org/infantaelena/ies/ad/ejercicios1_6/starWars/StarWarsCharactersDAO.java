package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import org.infantaelena.ies.ad.ejercicios1_6.starWars.exceptions.*;

import java.util.List;

public interface StarWarsCharactersDAO {
    void create(StarWarsCharacter character) throws StarWarsDuplicateCharacterException, StarWarsCannotCreateException;

    StarWarsCharacter read(String name) throws StarWarsCannotReadException;

    List<StarWarsCharacter> readAll() throws StarWarsCannotReadException;

    boolean update(StarWarsCharacter character) throws StarWarsCannotUpdateException;

    boolean delete(StarWarsCharacter character) throws StarWarsCannotDeleteException;
}

