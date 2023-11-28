package org.infantaelena.ies.psp.UD2.practicas.ejercicioFilosofos2;

public class Filosofo extends Thread{
    private Mesa mesa;
    private int posicionMesa;
    private Palillo palilloIzq;
    private Palillo palilloDer;

    public Filosofo(Mesa mesa, int posicionMesa, Palillo palilloIzq, Palillo palilloDer){
        this.mesa = mesa;
        this.posicionMesa = posicionMesa;
        this.palilloIzq = palilloIzq;
        this.palilloDer = palilloDer;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public int getPosicionMesa() {
        return posicionMesa;
    }

    public void setPosicionMesa(int posicionMesa) {
        this.posicionMesa = posicionMesa;
    }

    public Palillo getPalilloIzq() {
        return palilloIzq;
    }

    public void setPalilloIzq(Palillo palilloIzq) {
        this.palilloIzq = palilloIzq;
    }

    public Palillo getPalilloDer() {
        return palilloDer;
    }

    public void setPalilloDer(Palillo palilloDer) {
        this.palilloDer = palilloDer;
    }
    @Override
    public void run(){
        int max = 3;
        int min = 1;

        while(true){
            System.out.println("Filósofo " + posicionMesa + " pensando");
            esperar(max, min);
            System.out.println("Filósofo " + posicionMesa + " intentando comer");
            this.mesa.cogerPalillos(this);
            System.out.println("Filósofo " + posicionMesa + " comiendo");
            esperar(max, min);
            this.mesa.soltarPalillos(this);
            System.out.println("Filósofo " + posicionMesa + " terminó de comer");
            /*palilloIzq.soltarPalillo();
            palilloDer.soltarPalillo();
            Filosofo[] filosofos = mesa.getFilosofos();
            try {
                filosofos[posicionMesa - 1].notify();
            }catch (IndexOutOfBoundsException e){
                filosofos[filosofos.length - 1].notify();

            }
            try {
                filosofos[posicionMesa + 1].notify();
            }catch (IndexOutOfBoundsException e){
                filosofos[0].notify();
            }*/
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