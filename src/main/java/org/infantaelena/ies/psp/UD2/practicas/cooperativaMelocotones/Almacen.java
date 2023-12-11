package org.infantaelena.ies.psp.UD2.practicas.cooperativaMelocotones;

public class Almacen {
    public static final int CAPACIDAD_ALMACEN = 200;
    private int cajasEnElAlmacen;

    public int getCajasEnElAlmacen() {
        return cajasEnElAlmacen;
    }

    public void setCajasEnElAlmacen(int cajasEnElAlmacen) {
        this.cajasEnElAlmacen = cajasEnElAlmacen;
    }

    public Almacen(){
        this.cajasEnElAlmacen = 0;
    }

    public Almacen(int numeroCajas) {
        this.cajasEnElAlmacen = numeroCajas;
    }

    public synchronized void almacenarCajas(String agricultor, int cajas){
        while (getCajasEnElAlmacen() + cajas > CAPACIDAD_ALMACEN){
            try {
                wait();
                System.out.println(agricultor + " está esperando a que haya sitio en el almacén.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setCajasEnElAlmacen(getCajasEnElAlmacen() + cajas);
        System.out.printf("%s deposita en el almacén %d cajas.%n", agricultor, cajas);
        notifyAll();
    }

    public synchronized void comprarCajas(String cliente, int cajas){
        while (getCajasEnElAlmacen() - cajas < 0){
            try {
                wait();
                System.out.println(cliente + " está esperando a que haya cajas disponibles para comprar.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setCajasEnElAlmacen(getCajasEnElAlmacen() - cajas);
        System.out.printf("%s ha comprado %d cajas.%n", cliente, cajas);
        notifyAll();
    }

    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        for (int i = 1; i <= 3; i++) {
            Agricultor agricultor = new Agricultor("Agricultor " + i, almacen);
            agricultor.start();
        }

        for (int i = 1; i <= 3; i++) {
            Cliente cliente = new Cliente("Cliente " + i, almacen);
            cliente.start();
        }



    }
}
