package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import java.util.List;

public interface StarWarsCharactersDAO {
    void create(StarWarsCharacter character);

    StarWarsCharacter read(String name);

    List<StarWarsCharacter> readAll();

    boolean update(StarWarsCharacter character);

    boolean delete(StarWarsCharacter character);
}

