package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class HiloServidor extends Thread{
    private final Socket socket;

    public HiloServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream())
        ){
            String str = dataInputStream.readUTF();
            while (!str.equals("0")){

                switch (str.trim().toUpperCase()){
                    case "F":
                        dataOutputStream.writeUTF(LocalDate.now().toString());
                        break;
                    case "H":
                        dataOutputStream.writeUTF(LocalTime.now().toString());
                        break;
                    default:
                        dataOutputStream.writeUTF("ERROR");
                }

                str = dataInputStream.readUTF();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
