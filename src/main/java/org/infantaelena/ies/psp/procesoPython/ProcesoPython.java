package org.infantaelena.ies.psp.procesoPython;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesoPython {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("python", "src/main/resources/proceso_python.py");

        try {
            Process p = pb.start();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));){
                String line;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
