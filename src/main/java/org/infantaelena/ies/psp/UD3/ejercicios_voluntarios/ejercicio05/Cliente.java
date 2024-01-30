package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 6000);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())
        ){
            System.out.println("Haz tu consulta");
            String entrada = in.nextLine();
            while (!entrada.trim().equals("0")){

                dataOutputStream.writeUTF(entrada);

                System.out.println(dataInputStream.readUTF());

                System.out.println("Haz tu consulta");
                entrada = in.nextLine();
            }
            dataOutputStream.writeUTF(entrada.trim());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
