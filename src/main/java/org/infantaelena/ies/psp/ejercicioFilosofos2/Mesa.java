package org.infantaelena.ies.psp.ejercicioFilosofos2;

public class Mesa {
    private org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo[] filosofos;
    private org.infantaelena.ies.psp.ejercicioFilosofos2.Palillo[] palillos;

    public Mesa(int comensales){
        this.filosofos = new org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo[comensales];
        palillos = new org.infantaelena.ies.psp.ejercicioFilosofos2.Palillo[comensales];
        for (int i = 0; i < comensales; i++) {
            palillos[i] = new org.infantaelena.ies.psp.ejercicioFilosofos2.Palillo();
        }
        for (int i = 0; i < filosofos.length; i++) {
            org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo filosofo = null;
            try {
                filosofo = new org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo(this, i, palillos[i], palillos[i + 1]);
            }catch (IndexOutOfBoundsException e){
                filosofo = new org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo(this, i, palillos[i], palillos[0]);
            }
            filosofos[i] = filosofo;
        }
    }

    public org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo[] getFilosofos() {
        return filosofos;
    }

    public void setFilosofos(org.infantaelena.ies.psp.ejercicioFilosofos2.Filosofo[] filosofos) {
        this.filosofos = filosofos;
    }

    public org.infantaelena.ies.psp.ejercicioFilosofos2.Palillo[] getPalillos() {
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