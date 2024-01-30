package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;
        try (ServerSocket servidor = new ServerSocket(puerto)){

            ArrayList<HiloServidor> hilos = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Socket cliente = servidor.accept();
                HiloServidor hilo = new HiloServidor(cliente);
                hilos.add(hilo);
                hilo.start();
            }

            //No podemos dejar que el servidor se cierre hasta que todos los hilos hayan terminado
            for (HiloServidor hilo : hilos) {
                hilo.join();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
