package org.infantaelena.ies.psp.ejercicioFilosofos;

public class Mesa {
    private Filosofo[] filosofos;
    private Palillo[] palillos;

    public Mesa(Filosofo[] filosofos){
        this.filosofos = filosofos;
        int comensales = filosofos.length;
        palillos = new Palillo[comensales];
        for (int i = 0; i < comensales; i++) {
            palillos[i] = new Palillo();
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
}
