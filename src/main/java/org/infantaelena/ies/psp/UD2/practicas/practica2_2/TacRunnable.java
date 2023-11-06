package org.infantaelena.ies.psp.UD2.practicas.practica2_2;

public class TacRunnable implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("TAC");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
