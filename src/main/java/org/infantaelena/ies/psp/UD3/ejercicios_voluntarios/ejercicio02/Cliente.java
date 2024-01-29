package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            System.out.println("Mi dirección: " + miDireccion + " - Mi puerto: " + miPuerto);
            System.out.println("Dirección del servidor: " + direccionServidorRemoto + " - Puerto del servidor: " + puertoServidorRemoto);

            try (DataInputStream dis = new DataInputStream(socket.getInputStream()); DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
                int numero = Integer.MAX_VALUE;
                while (numero != 0) {
                    numero = pedirNumero();
                    if (numero != 0){
                        dos.writeInt(numero);
                        System.out.println(dis.readUTF());
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int pedirNumero() {
        Scanner sc = new Scanner(System.in);
        int numero;
        System.out.print("Introduce un número (0 para terminar): ");
        try {
            numero = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Se ha producido un error. Por favor, introduce un número.");
            numero = pedirNumero();
        }
        return numero;
    }
}
