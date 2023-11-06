package org.infantaelena.ies.psp.UD2.practicas.practica2_2;

public class TicRunnable implements Runnable{
    @Override
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
