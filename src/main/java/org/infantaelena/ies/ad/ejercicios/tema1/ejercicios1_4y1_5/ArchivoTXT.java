package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_4y1_5;

import java.io.*;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArchivoTXT {

    //Atributos
    private Path path;

    //Constructores
    public ArchivoTXT(String pathString) throws IllegalArgumentException{

        try {

            this.path = Paths.get(pathString);

            if (Files.exists(path)) {
                if (Files.isDirectory(path)) {
                    throw new IllegalArgumentException("La ruta proporcionada no hace referencia a un archivo.");
                }
            } else {
                throw new IllegalArgumentException("El archivo no existe.");
            }
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("La ruta proporcionada no es válida.");
        }
    }

    //Getters y Setters
    public Path getPath() {
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
    }

    //Métodos

    //1.4.2
    public String aVerso() {
        StringBuilder verso = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(this.path)){
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
            String[] oraciones = contenido.toString().split("\\.");

            for (String oracion : oraciones) {
                verso.append(oracion.trim()).append(".\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error en E/S");
        }
        return verso.toString();
    }

    //1.4.3
    public void codifica(String archivoDestino){

        File file = new File(archivoDestino);

        try (FileReader fr = new FileReader(path.toFile());
             FileWriter fw = new FileWriter(file)) {
            int i;
            while ((i = fr.read()) != -1) {
                char c = (char) i;
                if (!esVocal(c)) {
                    fw.write(c);
                }
            }
        }catch (IOException e){
            throw new RuntimeException("Error en E/S");
        }
    }

    public void codificaBuffer(String archivoDestino){

        File file = new File(archivoDestino);

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    if (!esVocal(c)) {
                        bw.write(c);
                    }
                }
                bw.newLine();
            }
        }catch (IOException e){
            throw new RuntimeException("Error en E/S");
        }
    }

    public void codificaFiles(String archivoDestino){

        Path file = Paths.get(archivoDestino);

        try (BufferedReader br = Files.newBufferedReader(path);
             BufferedWriter bw = Files.newBufferedWriter(file)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    if (!esVocal(c)) {
                        bw.write(c);
                    }
                }
                bw.newLine();
            }
        }catch (IOException e){
            throw new RuntimeException("Error en E/S");
        }
    }

    private boolean esVocal(char caracter) {
        char c = Character.toLowerCase(caracter);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú';
    }

    //1.4.4
    public void mover(String nuevaRuta) throws IllegalArgumentException{

        Path directorio = this.path.getParent();

        try{
            Path directorioDestino = Paths.get(nuevaRuta);
            if (!Files.isDirectory(directorioDestino)){
                throw new IllegalArgumentException("La ruta proporcionada no es un directorio.");
            }
        }catch (InvalidPathException e){
            throw new IllegalArgumentException("La ruta proporcionada no es válida.");
        }

        Path ruta = Paths.get(nuevaRuta).resolve(this.path.getFileName());

        try {
            Files.move(this.path, ruta);
            setPath(ruta);
            //Si el directorio no está vacío lanza DirectoryNotEmptyException.
            try {
                Files.delete(directorio);
            }catch (DirectoryNotEmptyException ignored){

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //1.4.5
    public int contar(){

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return contenido.length();
    }

    public int contarLetras(){
        Pattern patronLetras = Pattern.compile("[a-zA-Z]");
        int contador = 0;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Matcher matcher = patronLetras.matcher(linea);
                while (matcher.find()){
                    contador++;
                }

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return contador;
    }

    public int contarPuntuacion(){
        Pattern patronPuntuacion = Pattern.compile("[.,!?;:()\\[\\]{}\"'<>]");
        int contador = 0;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Matcher matcher = patronPuntuacion.matcher(linea);
                while (matcher.find()){
                    contador++;
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return contador;
    }

    //1.4.6
    public int contarLineas(){
        String texto = aVerso();
        String[] split = texto.split("\\.");
        return split.length;
    }

    //1.4.7
    public int cuentaPalabras(){
        int contador = 0;
        try (BufferedReader br = Files.newBufferedReader(this.path)){
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
            String[] palabras = contenido.toString().split("\\s+");

            contador = palabras.length;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }

    // 1.4.8
    public void cuentaVocales() {

        Path numVocales = path.getParent().resolve("numVocales.txt");

        try (BufferedReader br = Files.newBufferedReader(path);
             BufferedWriter bw = Files.newBufferedWriter(numVocales)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");

                for (String palabra : palabras) {
                    int contadorVocales = contarVocales(palabra);
                    bw.write(contadorVocales + " ");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al contar las vocales y escribir en el archivo numVocales.txt", e);
        }
    }

    private int contarVocales(String palabra) {
        Pattern patronVocales = Pattern.compile("[aeiouAEIOU]");
        Matcher matcher = patronVocales.matcher(palabra);
        int contador = 0;
        while (matcher.find()) {
            contador++;
        }
        return contador;
    }

    // 1.4.9
    public void cuentaVocalesTotal() {

        Path numVocales = path.getParent().resolve("numVocalesTotal.txt");

        try (BufferedReader br = Files.newBufferedReader(path);
             BufferedWriter bw = Files.newBufferedWriter(numVocales)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");

                for (String palabra : palabras) {
                    int contadorVocales = contarVocalesTotales(palabra);
                    bw.write(contadorVocales + " ");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al contar las vocales y escribir en el archivo numVocalesTotal.txt", e);
        }
    }

    private int contarVocalesTotales(String palabra) {
        Pattern patronVocales = Pattern.compile("[aeiouáéíóúAEIOUÁÉÍÓÚüÜ]");
        Matcher matcher = patronVocales.matcher(palabra);
        int contador = 0;
        while (matcher.find()) {
            contador++;
        }
        return contador;
    }

    //1.4.10
    public void frecuenciaLetras() {
        int[] frecuencia = new int[26];

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char letraMinuscula = Character.toLowerCase(c);
                        int indice = letraMinuscula - 'a';
                        frecuencia[indice]++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }

        for (char letra = 'a'; letra <= 'z'; letra++) {
            int indice = letra - 'a';
            System.out.println(letra + ": " + frecuencia[indice]);
        }
    }

    public static void main(String[] args) {

        ArchivoTXT archivoTXT = new ArchivoTXT("src/main/resources/Lorem ipsum.txt");

        System.out.println(archivoTXT.contar());
        System.out.println(archivoTXT.contarLetras());
        System.out.println(archivoTXT.contarPuntuacion());
        System.out.println(archivoTXT.contarLineas());
        System.out.println(archivoTXT.cuentaPalabras());
        archivoTXT.cuentaVocales();
        archivoTXT.cuentaVocalesTotal();
        archivoTXT.frecuenciaLetras();

    }
}