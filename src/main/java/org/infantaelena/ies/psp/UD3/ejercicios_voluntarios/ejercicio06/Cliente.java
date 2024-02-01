package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio06;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try (DatagramSocket servidor = new DatagramSocket((int) (Math.random() * 1000 + 6001))) {

            System.out.println("Haz tu consulta");
            String entrada = in.nextLine();
            while (!entrada.trim().equals("FIN")){

                byte[] input = entrada.getBytes();
                DatagramPacket packet = new DatagramPacket(input, input.length, InetAddress.getLocalHost(), 6000);
                servidor.send(packet);

                byte[] buffer = new byte[1024];
                DatagramPacket received = new DatagramPacket(buffer, buffer.length);
                servidor.receive(received);

                System.out.println(new String(received.getData()));

                System.out.println("Haz tu consulta");
                entrada = in.nextLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
