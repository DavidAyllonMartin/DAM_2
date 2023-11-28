package org.infantaelena.ies.psp.UD2.practicas.ejercicioFilosofos2;

public class Mesa {
    private Filosofo[] filosofos;
    private Palillo[] palillos;

    public Mesa(int comensales){
        this.filosofos = new Filosofo[comensales];
        palillos = new Palillo[comensales];
        for (int i = 0; i < comensales; i++) {
            palillos[i] = new Palillo();
        }
        for (int i = 0; i < filosofos.length; i++) {
            Filosofo filosofo = null;
            try {
                filosofo = new Filosofo(this, i, palillos[i], palillos[i + 1]);
            }catch (IndexOutOfBoundsException e){
                filosofo = new Filosofo(this, i, palillos[i], palillos[0]);
            }
            filosofos[i] = filosofo;
        }
    }

    public Filosofo[] getFilosofos() {
        return filosofos;
    }

    public void setFilosofos(Filosofo[] filosofos) {
        this.filosofos = filosofos;
    }

    public Palillo[] getPalillos() {
        return palillos;
    }

    public void setPalillos(Palillo[] palillos) {
        this.palillos = palillos;
    }

    public synchronized void cogerPalillos(Filosofo filosofo){
        Palillo palilloIzq = filosofo.getPalilloIzq();
        Palillo palilloDer = filosofo.getPalilloDer();
        int posicionMesa = filosofo.getPosicionMesa();
        while (palilloIzq.estaEnUso() || palilloDer.estaEnUso()){
            try {
                System.out.println("Filósofo " + posicionMesa + " esperando a los palillos");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        palilloIzq.cogerPalillo();
        palilloDer.cogerPalillo();
    }
    public synchronized void soltarPalillos(Filosofo filosofo){
        Palillo palilloIzq = filosofo.getPalilloIzq();
        Palillo palilloDer = filosofo.getPalilloDer();
        palilloIzq.soltarPalillo();
        palilloDer.soltarPalillo();
        notifyAll();
    }

    public static void main(String[] args) {
        Mesa mesa = new Mesa(4);
        for (Filosofo filosofo : mesa.getFilosofos()){
            filosofo.start();
        }
    }
}