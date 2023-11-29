package org.infantaelena.ies.psp.UD2.practicas.hinchadoresGlobos;

public class SimulacionGlobos {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        for (int i = 1; i <= 4; i++) {
            Hinchador hinchador = new Hinchador("Hinchador " + i, mesa);
            hinchador.start();
        }

        for (int i = 1; i <= 2; i++) {
            Pinchador pinchador = new Pinchador("Pinchador " + i, mesa);
            pinchador.start();
        }
    }
}

