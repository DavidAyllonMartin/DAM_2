package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class StarWarsCharacter implements Externalizable {
    private String name;
    private String gender;
    private String birthYear;
    private int height;
    private double mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String planet;
    private String species;

    public StarWarsCharacter(String name, String gender, String birthYear, int height, double mass, String hairColor, String skinColor, String eyeColor, String planet, String species) {
        setName(name);
        setGender(gender);
        setBirthYear(birthYear);
        setHeight(height);
        setMass(mass);
        setHairColor(hairColor);
        setSkinColor(skinColor);
        setEyeColor(eyeColor);
        setPlanet(planet);
        setSpecies(species);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws IllegalArgumentException{
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be null or empty");
        }
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) throws IllegalArgumentException{
        if (birthYear == null || birthYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Birth year cannot be null or empty");
        }
        this.birthYear = birthYear;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws IllegalArgumentException{
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be a positive non-zero value");
        }
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) throws IllegalArgumentException{
        if (mass <= 0) {
            throw new IllegalArgumentException("Mass must be a positive non-zero value");
        }
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) throws IllegalArgumentException{
        if (hairColor == null || hairColor.trim().isEmpty()) {
            throw new IllegalArgumentException("Hair color cannot be null or empty");
        }
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) throws IllegalArgumentException{
        if (skinColor == null || skinColor.trim().isEmpty()) {
            throw new IllegalArgumentException("Skin color cannot be null or empty");
        }
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) throws IllegalArgumentException{
        if (eyeColor == null || eyeColor.trim().isEmpty()) {
            throw new IllegalArgumentException("Eye color cannot be null or empty");
        }
        this.eyeColor = eyeColor;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) throws IllegalArgumentException{
        if (planet == null || planet.trim().isEmpty()) {
            throw new IllegalArgumentException("Planet cannot be null or empty");
        }
        this.planet = planet;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) throws IllegalArgumentException{
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be null or empty");
        }
        this.species = species;
    }

    @Override
    public String toString() {
        return "StarWarsCharacter{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", height=" + height +
                ", mass=" + mass +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", planet='" + planet + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getGender());
        out.writeObject(getBirthYear());
        out.writeInt(getHeight());
        out.writeDouble(getMass());
        out.writeObject(getHairColor());
        out.writeObject(getSkinColor());
        out.writeObject(getEyeColor());
        out.writeObject(getPlanet());
        out.writeObject(getSpecies());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setGender((String) in.readObject());
        setBirthYear((String) in.readObject());
        setHeight(in.readInt());
        setMass(in.readDouble());
        setHairColor((String) in.readObject());
        setSkinColor((String) in.readObject());
        setEyeColor((String) in.readObject());
        setPlanet((String) in.readObject());
        setSpecies((String) in.readObject());
    }
}
