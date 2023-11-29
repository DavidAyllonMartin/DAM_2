package org.infantaelena.ies.psp.UD2.practicas.cooperativaMelocotones;

import java.util.Random;

public class Cliente extends Thread{
    private String nombre;
    private Almacen almacen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Cliente(String nombre, Almacen almacen) {
        setNombre(nombre);
        setAlmacen(almacen);
    }

    @Override
    public void run() {
        while (true) {
            int cantidad = new Random().nextInt(6) + 5; // Entre 5 y 10 cajas
            getAlmacen().comprarCajas(getNombre(), cantidad);
            try {
                Thread.sleep(new Random().nextInt(1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
