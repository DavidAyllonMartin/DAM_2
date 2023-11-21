package org.infantaelena.ies.psp.ejercicioRelevos;

public class Relevos {
    private Testigo testigo;
    private Corredor[] corredores;

    public Relevos(){
        this.testigo = new Testigo();
        this.corredores = new Corredor[4];
        for (int i = 0; i < 4; i++) {
            this.corredores[i] = new Corredor(this);
        }
    }

    public Corredor[] getCorredores() {
        return corredores;
    }

    public void cogerTestigo(Corredor corredor){
        synchronized (this.testigo) {
            while (this.testigo == null){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            corredor.setTestigo(this.testigo);
            this.testigo = null;
        }
    }

    public synchronized void soltarTestigo(Corredor corredor){
        this.testigo = corredor.getTestigo();
        corredor.setTestigo(null);
        notifyAll();
    }

    public static void main(String[] args) {
        Relevos carrera = new Relevos();
        for (Corredor corredor : carrera.getCorredores()){
            corredor.start();
            /*try {
                corredor.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
        System.out.println("La carrera ha terminado");
    }
}
