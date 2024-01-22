package org.infantaelena.ies.psp.UD3.ejemplos.ejercicios_voluntarios.ejercicio01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puertoServidor = 6000;

        try (Socket socket = new Socket(direccionServidor, puertoServidor)) {

            String miDireccion = socket.getLocalAddress().getHostAddress();
            int miPuerto = socket.getLocalPort();
            String direccionServidorRemoto = socket.getInetAddress().getHostAddress();
            int puertoServidorRemoto = socket.getPort();

            System.out.println("Se inicia el proceso cliente");
            System.out.println("Datos de la conexión establecida:");
            System.out.println("Mi dirección: " + miDireccion);
            System.out.println("Mi puerto: " + miPuerto);
            System.out.println("Dirección del servidor: " + direccionServidorRemoto);
            System.out.println("Puerto del servidor: " + puertoServidorRemoto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
