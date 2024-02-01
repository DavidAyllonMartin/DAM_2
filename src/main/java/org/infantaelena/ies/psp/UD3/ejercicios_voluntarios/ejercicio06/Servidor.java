package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio06;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDate;
import java.time.LocalTime;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;
        try (DatagramSocket servidor = new DatagramSocket(puerto)){

            byte[] buffer = new byte[1024];
            DatagramPacket received = new DatagramPacket(buffer, buffer.length);
            servidor.receive(received);

            String str = new String(received.getData());

            while (true){

                DatagramPacket sended;

                switch (str.trim().toUpperCase()){
                    case "F":
                        byte[] date = LocalDate.now().toString().getBytes();
                        sended = new DatagramPacket(date, date.length, received.getAddress(), received.getPort());
                        servidor.send(sended);
                        break;
                    case "H":
                        byte[] time = LocalTime.now().toString().getBytes();
                        sended = new DatagramPacket(time, time.length, received.getAddress(), received.getPort());
                        servidor.send(sended);
                        break;
                    default:
                        byte[] error = "ERROR".getBytes();
                        sended = new DatagramPacket(error, error.length, received.getAddress(), received.getPort());
                        servidor.send(sended);
                }

                servidor.receive(received);
                str = new String(received.getData());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
