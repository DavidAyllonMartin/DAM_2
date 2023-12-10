package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicios4_4 {
    public boolean AgregarPokemonBBDD(Connection connection, PokemonTema4 pokemon){
        boolean agregado = false;
        try {
            String insertPokemon = "INSERT INTO pokemon (idNum, name, HP, speed, attack, special_attack, defense, special_defense, evolve_id, generation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String insertType = "INSERT INTO pokemon_type (pokemon_id, type1, type2) VALUES (?, ?, ?)";
            try (PreparedStatement pokemonPs = connection.prepareStatement(insertPokemon); PreparedStatement typePs = connection.prepareStatement(insertType)){
                connection.setAutoCommit(false);
                pokemonPs.setInt(1, pokemon.getId());
                pokemonPs.setString(2, pokemon.getName());
                pokemonPs.setInt(3, pokemon.getHp());
                pokemonPs.setInt(4, pokemon.getSpeed());
                pokemonPs.setInt(5, pokemon.getAttack());
                pokemonPs.setInt(6, pokemon.getSpecialAttack());
                pokemonPs.setInt(7, pokemon.getDefense());
                pokemonPs.setInt(8, pokemon.getSpecialDefense());
                pokemonPs.setInt(9, pokemon.getEvolveId());
                pokemonPs.setInt(10, pokemon.getGeneration());
                pokemonPs.executeUpdate();

                typePs.setInt(1, 1);
                typePs.setString(2, pokemon.getType1());
                typePs.setString(3, pokemon.getType2());
                typePs.executeUpdate();

                connection.commit();
                agregado = true;

            } catch (SQLException e) {
                connection.rollback();
            }finally {
                connection.setAutoCommit(true);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error en rollback o autocommit true");
        }
        return agregado;
    }
}
