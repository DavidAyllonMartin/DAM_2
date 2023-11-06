package org.infantaelena.ies.psp.ejercicioFilosofos;

public class Filosofo extends Thread{
    private Mesa mesa;
    private int posicionMesa;
    private final Palillo palilloIzq;
    private final Palillo palilloDer;

    public Filosofo(Mesa mesa, int posicionMesa, Palillo palilloIzq, Palillo palilloDer){
        this.mesa = mesa;
        this.posicionMesa = posicionMesa;
        this.palilloIzq = palilloIzq;
        this.palilloDer = palilloDer;
    }

    public void cogerPalillos(){
        synchronized (palilloIzq) {
            while (palilloIzq.estaEnUso()) {
                try {
                    System.out.println("Filósofo " + posicionMesa + " esperando a su palillo izquierdo");
                    palilloIzq.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Filósofo " + posicionMesa + " coge su palillo izquierdo");
            palilloIzq.cogerPalillo();
        }

        synchronized (palilloDer) {
            while (palilloDer.estaEnUso()) {
                try {
                    System.out.println("Filósofo " + posicionMesa + " esperando a su palillo derecho");
                    palilloDer.wait();
                } catch (InterruptedException e) {
                    palilloIzq.soltarPalillo();
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Filósofo " + posicionMesa + " coge su palillo derecho");
            palilloDer.cogerPalillo();
        }
    }

    public void soltarPalillos(){
        synchronized (palilloIzq) {
            palilloIzq.soltarPalillo();
            palilloIzq.notifyAll();
        }
        synchronized (palilloDer) {
            palilloDer.soltarPalillo();
            palilloDer.notifyAll();
        }
    }

    @Override
    public void run(){
        int max = 3;
        int min = 1;

        while(true){
            System.out.println("Filósofo " + posicionMesa + " pensando");
            esperar(max, min);
            System.out.println("Filósofo " + posicionMesa + " intentando comer");
            cogerPalillos();
            System.out.println("Filósofo " + posicionMesa + " comiendo");
            esperar(max, min);
            soltarPalillos();
            System.out.println("Filósofo " + posicionMesa + " terminó de comer");
        }
    }

    private static void esperar(int max, int min) {
        int aleatorio = (int) (Math.random() * (max - min + 1)) + min;
        int tiempoEspera = aleatorio * 1000;
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
