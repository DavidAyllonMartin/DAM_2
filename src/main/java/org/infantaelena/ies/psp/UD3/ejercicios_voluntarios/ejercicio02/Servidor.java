package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            System.out.println("Mi dirección: " + ipLocal.getHostAddress() + "- Mi puerto: " + puertoLocal);
            System.out.println("Dirección del cliente: " + ipRemota.getHostAddress() + "- Puerto del cliente: " + puertoRemoto);

            try (DataInputStream dis = new DataInputStream(cliente.getInputStream()); DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
                int numero = Integer.MAX_VALUE;
                while (numero != 0){
                    numero = dis.readInt();
                    if (numero != 0){
                        String retorno = String.format("El doble de %d es %d", numero, numero * 2);
                        dos.writeUTF(retorno);
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
