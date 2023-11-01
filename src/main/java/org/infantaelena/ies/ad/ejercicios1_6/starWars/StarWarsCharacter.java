package org.infantaelena.ies.ad.ejercicios1_6.starWars;

import org.infantaelena.ies.ad.ejercicios1_6.starWars.exceptions.InvalidStarWarsParameterException;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.InvalidPropertiesFormatException;
import java.util.Objects;

public class StarWarsCharacter implements Externalizable {
    private static final long serialVersionUID = 1L;
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

    public StarWarsCharacter() {
        this.name = "";
        this.gender = "";
        this.birthYear = "";
        this.height = 0;
        this.mass = 0.0;
        this.hairColor = "";
        this.skinColor = "";
        this.eyeColor = "";
        this.planet = "";
        this.species = "";
    }

    public StarWarsCharacter(String name, String gender, String birthYear, int height, double mass, String hairColor, String skinColor, String eyeColor, String planet, String species) throws InvalidStarWarsParameterException {
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

    public void setName(String name) throws InvalidStarWarsParameterException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws InvalidStarWarsParameterException {
        if (gender == null || gender.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Gender cannot be null or empty");
        }
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) throws InvalidStarWarsParameterException {
        if (birthYear == null || birthYear.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Birth year cannot be null or empty");
        }
        this.birthYear = birthYear;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws InvalidStarWarsParameterException {
        if (height < 0) {
            throw new InvalidStarWarsParameterException("Height must be a positive non-zero value");
        }
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) throws InvalidStarWarsParameterException {
        if (mass < 0) {
            throw new InvalidStarWarsParameterException("Mass must be a positive non-zero value");
        }
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) throws InvalidStarWarsParameterException {
        if (hairColor == null || hairColor.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Hair color cannot be null or empty");
        }
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) throws InvalidStarWarsParameterException {
        if (skinColor == null || skinColor.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Skin color cannot be null or empty");
        }
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) throws InvalidStarWarsParameterException {
        if (eyeColor == null || eyeColor.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Eye color cannot be null or empty");
        }
        this.eyeColor = eyeColor;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) throws InvalidStarWarsParameterException {
        if (planet == null || planet.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Planet cannot be null or empty");
        }
        this.planet = planet;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) throws InvalidStarWarsParameterException {
        if (species == null || species.trim().isEmpty()) {
            throw new InvalidStarWarsParameterException("Species cannot be null or empty");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StarWarsCharacter that = (StarWarsCharacter) o;

        return Objects.equals(name.trim(), that.name.trim());
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
        try {
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
        } catch (InvalidStarWarsParameterException e) {
            throw new InvalidPropertiesFormatException(e);
        }

    }

    public static class Builder {
        private String name = "Unknown";
        private String gender = "Unknown";
        private String birthYear = "Unknown";
        private int height = 0;
        private double mass = 0.0;
        private String hairColor = "Unknown";
        private String skinColor = "Unknown";
        private String eyeColor = "Unknown";
        private String planet = "Unknown";
        private String species = "Unknown";

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder hairColor(String hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder skinColor(String skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        public Builder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder planet(String planet) {
            this.planet = planet;
            return this;
        }

        public Builder species(String species) {
            this.species = species;
            return this;
        }

        public StarWarsCharacter build() throws InvalidStarWarsParameterException {
            return new StarWarsCharacter(this.name, this.gender, this.birthYear, this.height, this.mass, this.hairColor, this.skinColor, this.eyeColor, this.planet, this.species);
        }
    }
}
