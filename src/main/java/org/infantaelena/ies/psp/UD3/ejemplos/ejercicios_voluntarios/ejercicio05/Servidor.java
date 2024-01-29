package org.infantaelena.ies.psp.UD3.ejemplos.ejercicios_voluntarios.ejercicio05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;
        try (ServerSocket servidor = new ServerSocket(puerto)){

            Socket cliente = servidor.accept();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
