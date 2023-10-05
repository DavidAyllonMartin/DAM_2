package org.infantaelena.ies.ad.ejercicios1_4;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ArchivoTXT {

    //Atributos
    private Path path;

    //Constructores
    public ArchivoTXT(String pathString) throws IOException {

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
    public String aVerso() throws IOException {
        StringBuilder verso = new StringBuilder();
        try {
            byte[] contenido = Files.readAllBytes(path);
            String texto = new String(contenido, StandardCharsets.UTF_8);

            // Dividir el texto en oraciones (asumiendo que los puntos denotan el final de una oración)
            String[] oraciones = texto.split("\\.");


            for (String oracion : oraciones) {
                verso.append(oracion.trim()).append(".\n");
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo: " + e.getMessage());
        }
        return verso.toString();
    }

    public void codifica(String archivoDestino) throws IOException {
        try (FileReader fr = new FileReader(path.toFile());
             FileWriter fw = new FileWriter(archivoDestino)) {
            int c;
            while ((c = fr.read()) != -1) {
                char caracter = (char) c;
                if (!esVocal(caracter)) {
                    fw.write(caracter);
                }
            }
        }
    }

    public void codificaBuffer(String archivoDestino) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char caracter : linea.toCharArray()) {
                    if (!esVocal(caracter)) {
                        bw.write(caracter);
                    }
                }
                bw.newLine();
            }
        }
    }

    public void codificaFiles(String archivoDestino) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path);
             BufferedWriter writer = Files.newBufferedWriter(path)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                for (char caracter : linea.toCharArray()) {
                    if (!esVocal(caracter)) {
                        writer.write(caracter);
                    }
                }
                writer.newLine();
            }
        }
    }

    private boolean esVocal(char caracter) {
        char c = Character.toLowerCase(caracter);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

