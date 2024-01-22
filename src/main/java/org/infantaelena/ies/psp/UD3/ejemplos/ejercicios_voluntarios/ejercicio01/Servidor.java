package org.infantaelena.ies.psp.UD3.ejemplos.ejercicios_voluntarios.ejercicio01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args){
        int numeroServidor = 6000;

        try (ServerSocket servidor = new ServerSocket(numeroServidor); Socket cliente = servidor.accept()){

            InetAddress ipLocal = cliente.getLocalAddress();
            InetAddress ipRemota = cliente.getInetAddress();

            int puertoLocal = cliente.getLocalPort();
            int puertoRemoto = cliente.getPort();

            System.out.println("Se inicia el proceso servidor");
            System.out.println("Datos de la conexión establecida:");
            System.out.println("Mi dirección: " + ipLocal.getHostAddress());
            System.out.println("Mi puerto: " + puertoLocal);
            System.out.println("Dirección del cliente: " + ipRemota.getHostAddress());
            System.out.println("Puerto del cliente: " + puertoRemoto);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
