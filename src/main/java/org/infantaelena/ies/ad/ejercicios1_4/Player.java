package org.infantaelena.ies.ad.ejercicios1_4;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Player {
    private String playerName;
    private String position;
    private String team;
    private boolean rookie;
    private double age;
    private int seasonsExperience;
    private int pickRound;
    private int number;
    private int draftYear;
    private String college;

    public Player() {
        this.playerName = "";
        this.position = "";
        this.team = "";
        this.rookie = false;
        this.age = 0;
        this.seasonsExperience = 0;
        this.pickRound = 0;
        this.number = 0;
        this.draftYear = 0;
        this.college = "";
    }
    public Player(String playerName){
        this.playerName = playerName;
        this.position = "";
        this.team = "";
        this.rookie = false;
        this.age = 0;
        this.seasonsExperience = 0;
        this.pickRound = 0;
        this.number = 0;
        this.draftYear = 0;
        this.college = "";
    }
    public Player(String playerName, String position, String team, boolean rookie, double age, int seasonsExperience, int pickRound, int number, int draftYear, String college) {
        this(playerName);
        this.position = position;
        this.team = team;
        this.rookie = rookie;
        this.age = age;
        this.seasonsExperience = seasonsExperience;
        this.pickRound = pickRound;
        this.number = number;
        this.draftYear = draftYear;
        this.college = college;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public boolean isRookie() {
        return rookie;
    }
    public double getAge() {
        return age;
    }
    public void setAge(double age) {
        this.age = age;
    }
    public int getSeasonsExperience() {
        return seasonsExperience;
    }
    public void setSeasonsExperience(int seasonsExperience) {
        this.seasonsExperience = seasonsExperience;
    }
    public int getPickRound() {
        return pickRound;
    }
    public void setPickRound(int pickRound) {
        this.pickRound = pickRound;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getDraftYear() {
        return draftYear;
    }
    public void setDraftYear(int draftYear) {
        this.draftYear = draftYear;
    }
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }

    //Métodos

    //1.4.10
    public static boolean savePlayersCSV(Player[] players, String path){

        boolean saved = true;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            for (Player player : players){

                bw.write(playerToCSVLine(player));
                bw.newLine();

            }

        } catch (IOException e) {
            saved = false;
            throw new RuntimeException(e);
        }
        return saved;
    }
    private static String playerToCSVLine(Player player) {
        String playerName = player.getPlayerName();
        String position = player.getPosition();
        String team = player.getTeam();
        boolean rookie = player.isRookie();
        double age = player.getAge();
        int seasonsExperience = player.getSeasonsExperience();
        int pickRound = player.getPickRound();
        int number = player.getNumber();
        int draftYear = player.getDraftYear();
        String college = player.getCollege();

                /*StringBuilder sb = new StringBuilder();
                sb.append(playerName).append(";")
                        .append(position).append(";")
                        .append(team).append(";")
                        .append(rookie).append(";")
                        .append(age).append(";")
                        .append(seasonsExperience).append(";")
                        .append(pickRound).append(";")
                        .append(number).append(";")
                        .append(draftYear).append(";")
                        .append(college).append(";");

                String result = sb.toString();*/

        StringJoiner joiner = new StringJoiner(";");
        joiner.add(playerName)
                .add(position)
                .add(team)
                .add(String.valueOf(rookie))
                .add(String.valueOf(age))
                .add(String.valueOf(seasonsExperience))
                .add(String.valueOf(pickRound))
                .add(String.valueOf(number))
                .add(String.valueOf(draftYear))
                .add(college);

        return joiner.toString();
    }

    public static boolean savePlayer(Player player, String path){
        boolean saved = true;

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            StringBuilder strb = new StringBuilder();

            String line = null;
            while ((line = br.readLine()) != null){
                strb.append(line).append("\n");
            }

            strb.append(playerToCSVLine(player)).append("\n");

            //Si declaras el BufferedWriter en el try principal se borra el archivo y cuando intenta leer se encuentra un archivo vacío
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
                bw.write(strb.toString());
            }

        } catch (IOException e) {
            saved = false;
        }

        return saved;
    }
    public static boolean savePlayer(Player player, String path, OpenOption openOption){
        boolean saved = true;
        Path p = Paths.get(path);

        try (BufferedReader br = Files.newBufferedReader(p)){

            StringBuilder strb = new StringBuilder();

            String line = null;
            while ((line = br.readLine()) != null){
                strb.append(line).append("\n");
            }

            strb.append(playerToCSVLine(player)).append("\n");

            //Si declaras el BufferedWriter en el try principal se borra el archivo y cuando intenta leer se encuentra un archivo vacío
            try (BufferedWriter bw = Files.newBufferedWriter(p, openOption)){
                bw.write(strb.toString());
            }

        } catch (IOException e) {
            saved = false;
        }

        return saved;
    }


    @Override
    public String toString() {
        return "playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                ", rookie=" + rookie +
                ", age=" + age +
                ", seasonsExperience=" + seasonsExperience +
                ", pickRound=" + pickRound +
                ", number=" + number +
                ", draftYear=" + draftYear +
                ", college='" + college;
    }

    public static Player[] loadPlayersCSV(String path){
        ArrayList<Player> playerArrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = br.readLine()) != null){
                String[] atributes = line.split(";");
                Player player = new Player(atributes[0], atributes[1], atributes[2], Boolean.getBoolean(atributes[3]), Double.parseDouble(atributes[4]), Integer.parseInt(atributes[5]), Integer.parseInt(atributes[6]), Integer.parseInt(atributes[7]), Integer.parseInt(atributes[8]), atributes[9]);
                playerArrayList.add(player);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int playersSize = playerArrayList.size();

        Player[] players = new Player[playersSize];

        for (int i = 0; i < playersSize; i++) {
            players[i] = playerArrayList.get(i);
        }

        return players;
    }

    public static void showPlayersCSV(String path){
        Path p = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(p)) {

            String line = null;
            while ((line = br.readLine()) != null){
                String[] atributes = line.split(";");

                String playerName = atributes[0];
                String position = atributes[1];
                String team = atributes[2];
                boolean rookie = Boolean.getBoolean(atributes[3]);
                double age = Double.parseDouble(atributes[4]);
                int seasonsExperience = Integer.parseInt(atributes[5]);
                int pickRound = Integer.parseInt(atributes[6]);
                int number = Integer.parseInt(atributes[7]);
                int draftYear = Integer.parseInt(atributes[8]);
                String college = atributes[9];

                Player player = new Player(playerName, position, team, rookie, age, seasonsExperience, pickRound, number, draftYear, college);

                System.out.println(player);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {

        final String PLAYERS_CSV = "src/main/resources/players.csv";


        Player[] players = new Player[5];

        players[0] = new Player("Kirk Cousins", "QB", "Minnesota Vikings", false, 35.0, 11, 4, 8, 2012,"Michigan State");
        players[1] = new Player("Jonathan Taylor", "RB", "Indianapolis Colts", false, 24.6, 3, 2, 28, 2020,"Wisconsin");
        players[2] = new Player("David Montgomery", "RB", "Detroit Lions", false, 26.2, 4, 3, 5, 2019, "Iowa State");
        players[3] = new Player("Calvin Ridley", "WR", "Jacksonville Jaguars", false, 28.7, 5, 1, 0, 2018, "Alabama");
        players[4] = new Player("Mike Evans", "WR", "Tampa Bay Bucaneers", false, 30.0, 9, 1, 13, 2014, "Texas A&M");

        savePlayersCSV(players, PLAYERS_CSV);

        Player player = new Player("Mark Andrews", "TE", "Baltimore Ravens", false, 28.0, 5, 3, 89, 2018, "Oklahoma");

        savePlayer(player, PLAYERS_CSV);

        /*Player[] players = loadPlayersCSV("src/main/resources/players.csv");

        for (Player player : players) {
            System.out.println(player);
        }*/

        showPlayersCSV(PLAYERS_CSV);

        Player player2 = new Player("Mike Williams", "WR", "Los Angeles Chargers", false, 28.9, 6, 1, 81, 2017, "Clemson");

        savePlayer(player2,PLAYERS_CSV, StandardOpenOption.READ);

    }
}
