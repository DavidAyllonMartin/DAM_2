package org.infantaelena.ies.ad.ejercicios1_4;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
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

    //Constructors
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
    public Player(String playerName, String position, String team, boolean rookie, double age, int seasonsExperience, int pickRound, int number, int draftYear, String college) {
        setPlayerName(playerName);
        setPosition(position);
        setTeam(team);
        setRookie(rookie);
        setAge(age);
        setSeasonsExperience(seasonsExperience);
        setPickRound(pickRound);
        setNumber(number);
        setDraftYear(draftYear);
        setCollege(college);
    }

    //Getters and setters
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) throws IllegalArgumentException{
        if (playerName != null && !playerName.trim().isEmpty()) {
            this.playerName = playerName;
        } else {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) throws IllegalArgumentException {
        if (position != null && !position.trim().isEmpty()) {
            this.position = position;
        } else {
            throw new IllegalArgumentException("Position cannot be null or empty.");
        }
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) throws IllegalArgumentException{
        if (team != null && !team.trim().isEmpty()) {
            this.team = team;
        } else {
            throw new IllegalArgumentException("Team cannot be null or empty.");
        }
    }
    public boolean isRookie() {
        return rookie;
    }
    public void setRookie(boolean rookie){
        this.rookie = rookie;
    }
    public double getAge() {
        return age;
    }
    public void setAge(double age) throws IllegalArgumentException{
        if (age >= 18){
            this.age = age;
        }else {
            throw new IllegalArgumentException("Age cannot be under 18");
        }
    }
    public int getSeasonsExperience() {
        return seasonsExperience;
    }
    public void setSeasonsExperience(int seasonsExperience) throws IllegalArgumentException{
        if (seasonsExperience >= 0){
            this.seasonsExperience = seasonsExperience;
        }else{
            throw new IllegalArgumentException("Number of seasons cannot be negative");
        }
    }
    public int getPickRound() {
        return pickRound;
    }
    public void setPickRound(int pickRound) {
        if (pickRound >= 0 && pickRound <= 7){
            this.pickRound = pickRound;
        }else{
            throw new IllegalArgumentException("Draft round cannot be under 0 or over 7");
        }
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) throws IllegalArgumentException{
        if (number >= 0 && number <= 99){
            this.number = number;
        }else{
            throw new IllegalArgumentException("Number cannot be under 0 or over 99");
        }
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
        if (college != null && !college.trim().isEmpty()) {
            this.college = college;
        } else {
            throw new IllegalArgumentException("College cannot be null or empty.");
        }
    }

    //Methods

    //1.4.11
    /**
     * Reads an array of Player objects and saves them to a CSV file.
     *
     * @param players Array of Player objects to be saved
     * @param path Path of the CSV file
     * @return Returns true if it has managed to save all the players, and false if there has been any error with path or players
     * @throws IOException Throws an IOException if an I/O error occurs while writing
     */
    public static boolean savePlayersCSV(Player[] players, String path) throws IOException{
        boolean saved = true;
        Path p = checkPathFile(path);
        if (p != null && players != null){
            try (BufferedWriter bw = Files.newBufferedWriter(p)) {
                for (Player player : players) {
                    bw.write(playerToCSVLine(player));
                    bw.newLine();
                }
            }
        }else {
            saved = false;
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
    //1.4.12
    /**
     * Saves a Player object at the end of the specified file.
     *
     * @param player Player object to be saved
     * @param path Destination file path
     * @return Returns true if it has managed to save the player, and false if there has been any error with the path or player
     * @throws IOException Throws an IOException if an I/O error occurs while writing
     */
    public static boolean savePlayer(Player player, String path) throws IOException{
        boolean saved = true;
        Path p = checkPathFile(path);
        if (p != null && player != null){
            try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.APPEND)){
                bw.write(playerToCSVLine(player));
                bw.newLine();
            }
        }else {
            saved = false;
        }
        /*try (BufferedReader br = new BufferedReader(new FileReader(path))){
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
        }*/
        return saved;
    }
    private static boolean savePlayer(Player player, String path, OpenOption openOption) throws IOException{
        boolean saved = true;
        Path p = checkPathFile(path);
        if (p != null && player != null){
            try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.APPEND, openOption)){
                bw.write(playerToCSVLine(player));
                bw.newLine();
            }
        }else {
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
    //1.4.13
    /**
     * Reads a CSV file with saved Player objects and print them on the screen.
     *
     * @param path File path
     */
    public static void showPlayersCSV(String path){
        Path p = checkPathFile(path);
        if (p != null){
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
    }
    private static Path checkPathFile(String path){
        if (path == null || path.isEmpty()){
            return null;
        }
        Path p;
        try {
            p = Paths.get(path);
            if (Files.isDirectory(p)){
                p = null;
            }
        }catch (InvalidPathException e){
            p = null;
        }
        return p;
    }

    //1.5.1
    /**
     * Writes the player data to a binary file.
     *
     * @param path The path of the file where the player data will be written
     * @return True if the data is successfully written or false if the file path is not a file
     * @throws IOException Throws an IOException if an I/O error occurs during the writing process
     */
    public boolean writePlayerBIN(String path) throws IOException{
        boolean saved = true;
        Path p = checkPathFile(path);
        if (p != null){
            try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(p, StandardOpenOption.APPEND))){
                dos.writeUTF(getPlayerName());
                dos.writeUTF(getPosition());
                dos.writeUTF(getTeam());
                dos.writeBoolean(isRookie());
                dos.writeDouble(getAge());
                dos.writeInt(getSeasonsExperience());
                dos.writeInt(getPickRound());
                dos.writeInt(getNumber());
                dos.writeInt(getDraftYear());
                dos.writeUTF(getCollege());
            }
        }else {
            saved = false;
        }
        return saved;
    }
    //1.5.2
    public static boolean writePlayerListBIN(List<Player> players, String path) throws IOException{
        boolean saved = true;
        Path p = checkPathFile(path);
        if (p != null && players != null){
            try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(p))){

            }
            for (Player player : players){

            }
        }
        return saved;
    }
    public static void main(String[] args) {

        final String PLAYERS_CSV = "src/main/resources/players.csv";

        Player[] players = new Player[5];

        players[0] = new Player("Kirk Cousins", "QB", "Minnesota Vikings", false, 35.0, 11, 4, 8, 2012,"Michigan State");
        players[1] = new Player("Jonathan Taylor", "RB", "Indianapolis Colts", false, 24.6, 3, 2, 28, 2020,"Wisconsin");
        players[2] = new Player("David Montgomery", "RB", "Detroit Lions", false, 26.2, 4, 3, 5, 2019, "Iowa State");
        players[3] = new Player("Calvin Ridley", "WR", "Jacksonville Jaguars", false, 28.7, 5, 1, 0, 2018, "Alabama");
        players[4] = new Player("Mike Evans", "WR", "Tampa Bay Bucaneers", false, 30.0, 9, 1, 13, 2014, "Texas A&M");

        try {
            savePlayersCSV(players, PLAYERS_CSV);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Player player = new Player("Mark Andrews", "TE", "Baltimore Ravens", false, 28.0, 5, 3, 89, 2018, "Oklahoma");

        try {
            savePlayer(player, PLAYERS_CSV);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*Player[] players = loadPlayersCSV("src/main/resources/players.csv");
        for (Player player : players) {
            System.out.println(player);
        }*/

        showPlayersCSV(PLAYERS_CSV);

        Player player2 = new Player("Mike Williams", "WR", "Los Angeles Chargers", false, 28.9, 6, 1, 81, 2017, "Clemson");

        //IllegalArgumentException. No permite OpenOption READ en un BufferedWritter
        try {
            savePlayer(player2,PLAYERS_CSV, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}