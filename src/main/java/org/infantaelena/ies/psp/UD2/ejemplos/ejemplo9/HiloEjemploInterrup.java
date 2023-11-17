package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo9;

public class HiloEjemploInterrup extends Thread {

    public static void main(String[] args) {
        HiloEjemploInterrup h = new HiloEjemploInterrup();
        h.start();
        for (int i = 0; i < 2000000000; i++) ;//no hago nada
        h.interrumpir();
    }//

    public void run() {
        try {

            while (!isInterrupted()) {

                System.out.println("Inicio del bucle del hilo y hago mis cosas.");
                Thread.sleep(10);
                System.out.println("Fin del bucle del hilo... aquí ya habríamos terminado de hacer lo necesario y podríamos parar.");
            }
        } catch (InterruptedException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN");
        }

        System.out.println("FIN HILO");
    }//run

    public void interrumpir() {
        interrupt();
    }//interrumpir
}//