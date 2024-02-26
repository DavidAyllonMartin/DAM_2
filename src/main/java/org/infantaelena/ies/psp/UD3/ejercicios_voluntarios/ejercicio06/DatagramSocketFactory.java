package org.infantaelena.ies.psp.UD3.ejercicios_voluntarios.ejercicio06;

import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramSocketFactory {
    private static int port = 6001;

    public synchronized static DatagramSocket createDatagramSocket() throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        port++;
        return datagramSocket;
    }
}
