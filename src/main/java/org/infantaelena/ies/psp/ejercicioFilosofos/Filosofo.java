package org.infantaelena.ies.psp.ejercicioFilosofos;

public class Filosofo extends Thread{
    private final Mesa mesa;
    private final int posicionMesa;
    private final Palillo palilloIzq;
    private final Palillo palilloDer;

    public Filosofo(Mesa mesa, int posicionMesa, Palillo palilloIzq, Palillo palilloDer){
        this.mesa = mesa;
        this.posicionMesa = posicionMesa;
        this.palilloIzq = palilloIzq;
        this.palilloDer = palilloDer;
    }

    public synchronized boolean cogerPalillos(){
        if (palilloIzq.estaEnUso() || palilloDer.estaEnUso()){
            return false;
        }else {
            palilloIzq.cogerPalillo();
            palilloDer.cogerPalillo();
            return true;
        }
    }
    @Override
    public void run(){
        int max = 10;
        int min = 1;

        while(true){
            System.out.println("Filósofo " + posicionMesa + "pensando");
            esperar(max, min);
            System.out.println("Filósofo " + posicionMesa + "intentando comer");
            while (!cogerPalillos()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Filósofo " + posicionMesa + "comiendo");
            esperar(max, min);
            palilloIzq.soltarPalillo();
            palilloDer.soltarPalillo();
            try {
                mesa.getFilosofos()[posicionMesa - 1].notify();
            }catch (NullPointerException e){

            }
            mesa.getFilosofos()[posicionMesa + 1].notify();

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
