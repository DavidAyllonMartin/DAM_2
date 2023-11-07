package org.infantaelena.ies.psp.ejercicioRelevos;

public class Corredor extends Thread{
    private Relevos carrera;
    private Testigo testigo;
    public Corredor(Relevos carrera){
        this.carrera = carrera;
        this.testigo = null;
    }

    public Testigo getTestigo() {
        return testigo;
    }

    public void setTestigo(Testigo testigo) {
        this.testigo = testigo;
    }

    @Override
    public void run() {
        carrera.cogerTestigo(this);
        System.out.println(getName() + " ha empezado a correr.");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getName()+ " ha terminado de correr");
        carrera.soltarTestigo(this);
    }
}
