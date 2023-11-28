package org.infantaelena.ies.psp.UD2.practicas.ejercicioFilosofos2;

public class Palillo {
    private boolean enUso;

    public Palillo(){
        this.enUso = false;
    }

    public boolean estaEnUso() {
        return enUso;
    }

    public void cogerPalillo() {
        this.enUso = true;
    }
    public void soltarPalillo() {
        this.enUso = false;
    }
}
