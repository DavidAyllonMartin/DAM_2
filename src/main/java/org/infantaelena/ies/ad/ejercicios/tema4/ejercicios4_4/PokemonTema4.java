package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_4;

public class PokemonTema4 {

    //Attributes
    public static final int MAX_STAT = 255;
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int evolveId;
    private int generation;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    //Constructor
    public PokemonTema4(int id, String name, String type1, String type2, int evolveId, int generation, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        setId(id);
        setName(name);
        setType1(type1);
        setType2(type2);
        setEvolveId(evolveId);
        setGeneration(generation);
        setHp(hp);
        setAttack(attack);
        setDefense(defense);
        setSpecialAttack(specialAttack);
        setSpecialDefense(specialDefense);
        setSpeed(speed);
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = capitalizeFirstLetter(name);
    }

    public String getType1() {
        return this.type1;
    }

    public void setType1(String type1) {
        this.type1 = capitalizeFirstLetter(type1);
    }

    public String getType2() {
        return this.type2;
    }

    public void setType2(String type2) {
        this.type2 = capitalizeFirstLetter(type2);
    }

    public int getEvolveId() {
        return evolveId;
    }

    public void setEvolveId(int evolveId) {
        this.evolveId = evolveId;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private String capitalizeFirstLetter(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            StringBuilder result = new StringBuilder();
            boolean capitalizeNext = true;

            for (char c : s.toCharArray()) {
                if (Character.isWhitespace(c) || c == '-') {
                    capitalizeNext = true;
                    result.append(c);
                } else if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }

            return result.toString();
        }
    }

    public static class Builder {
        private int id = 0;
        private String name = "unknown";
        private String type1 = "unknown";
        private String type2 = "unknown";
        private int height = 0;
        private int weight = 0;
        private int hp = 0;
        private int attack = 0;
        private int defense = 0;
        private int specialAttack = 0;
        private int specialDefense = 0;
        private int speed = 0;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type1(String type1) {
            this.type1 = type1;
            return this;
        }

        public Builder type2(String type2) {
            this.type2 = type2;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder hp(int hp) {
            this.hp = hp;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder specialAttack(int specialAttack) {
            this.specialAttack = specialAttack;
            return this;
        }

        public Builder specialDefense(int specialDefense) {
            this.specialDefense = specialDefense;
            return this;
        }

        public Builder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public PokemonTema4 build() {
            return new PokemonTema4(id, name, type1, type2, height, weight, hp, attack, defense, specialAttack, specialDefense, speed);
        }
    }
}