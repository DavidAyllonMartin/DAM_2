package org.infantaelena.ies.psp.UD2.practicas.practica2_1;

public class TicThread extends Thread{
    public void run() {
        try {
            while (true) {
                System.out.println("TIC");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
