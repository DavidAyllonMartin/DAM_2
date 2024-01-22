package org.infantaelena.ies.psp.UD3.ejemplos.ejercicios_voluntarios.ejercicio03;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DescargaParalela {
    public static void main(String[] args) {
        if (args.length == 2){

            try {
                Path archivo = Paths.get(args[0]);
                if (!Files.isRegularFile(archivo)){

                }
                Path carpeta = Paths.get(args[1]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
