package org.infantaelena.ies.ad.ejercicios1_6.dao.pokemon;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author David Ayllón
 */
public class Pokemon implements Externalizable {
    public final int MAX_STAT = 255;
    public final int MIN_STAT = 1;
    public final int MAX_LEVEL = 100;
    public final int MIN_LEVEL = 1;
    private String name;
    private int level;
    private int health;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    //Constructor

    public Pokemon() {
        this("null", 1, 1, 1, 1, 1, 1, 1);

    }
    public Pokemon(String name, int level, int health, int attack, int defense, int specialAttack, int specialDefense, int speed) throws IllegalArgumentException{
        setName(name);
        setLevel(level);
        setHealth(health);
        setAttack(attack);
        setDefense(defense);
        setSpecialAttack(specialAttack);
        setSpecialDefense(specialDefense);
        setSpeed(speed);
    }
    public Pokemon(String[] atributos){
        this(atributos[0], Integer.parseInt(atributos[1]), Integer.parseInt(atributos[2]), Integer.parseInt(atributos[3]), Integer.parseInt(atributos[4]), Integer.parseInt(atributos[5]), Integer.parseInt(atributos[6]), Integer.parseInt(atributos[7]));
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name can not be empty");
        }
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws IllegalArgumentException{
        if (level < MIN_LEVEL || level > MAX_LEVEL){
            throw new IllegalArgumentException("Level must be between " + MIN_LEVEL + " and " + MAX_LEVEL);
        }
        this.level = level;
    }
    private boolean isStatInRange(int stat){
        boolean isCorrect = true;
        if (stat < MIN_STAT || stat > MAX_STAT){
            isCorrect = false;
        }
        return isCorrect;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws IllegalArgumentException{
        if (!isStatInRange(health)){
            throw new IllegalArgumentException("Health must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) throws IllegalArgumentException{
        if (!isStatInRange(attack)){
            throw new IllegalArgumentException("Attack must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) throws IllegalArgumentException{
        if (!isStatInRange(defense)){
            throw new IllegalArgumentException("Defense must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) throws IllegalArgumentException{
        if (!isStatInRange(specialAttack)){
            throw new IllegalArgumentException("Special attack must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) throws IllegalArgumentException{
        if (!isStatInRange(specialDefense)){
            throw new IllegalArgumentException("Special defense must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) throws IllegalArgumentException{
        if (!isStatInRange(speed)){
            throw new IllegalArgumentException("Speed must be beetween " + MIN_STAT + " and " + MAX_STAT);
        }
        this.speed = speed;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            out.writeUTF(getName());
            out.writeInt(getLevel());
            out.writeInt(getHealth());
            out.writeInt(getAttack());
            out.writeInt(getDefense());
            out.writeInt(getSpecialAttack());
            out.writeInt(getSpecialDefense());
            out.writeInt(getSpeed());
        } catch (IOException e) {
            throw new IOException("Error occurred while writing Pokemon object: " + e.getMessage());
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        try {
            setName(in.readUTF());
            setLevel(in.readInt());
            setHealth(in.readInt());
            setAttack(in.readInt());
            setDefense(in.readInt());
            setSpecialAttack(in.readInt());
            setSpecialDefense(in.readInt());
            setSpeed(in.readInt());
        } catch (IOException e) {
            throw new IOException("Error occurred while reading Pokemon object: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "Nombre:" + name + "\n" +
                "Nivel:" + level + "\n" +
                "HP:" + health + "\n" +
                "Ataque:" + attack + "\n" +
                "Defensa:" + defense + "\n" +
                "Ataque Especial:" + specialAttack + "\n" +
                "Defensa Especial:" + specialDefense + "\n" +
                "Velocidad:" + speed + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        return name.equals(pokemon.name);
    }
}
