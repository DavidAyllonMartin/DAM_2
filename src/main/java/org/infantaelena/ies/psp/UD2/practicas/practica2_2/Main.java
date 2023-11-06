package org.infantaelena.ies.psp.UD2.practicas.practica2_2;

public class Main {
    public static void main(String[] args) {
        new Thread(new TicRunnable()).start();
        new Thread(new TacRunnable()).start();

    }
}
