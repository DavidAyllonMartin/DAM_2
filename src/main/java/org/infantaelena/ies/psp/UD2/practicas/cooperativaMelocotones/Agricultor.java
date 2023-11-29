package org.infantaelena.ies.psp.UD2.practicas.cooperativaMelocotones;

import java.util.Random;

public class Agricultor extends Thread{
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

    public Agricultor(String nombre, Almacen almacen) {
        setNombre(nombre);
        setAlmacen(almacen);
    }

    @Override
    public void run() {
        while (true) {
            int cantidad = new Random().nextInt(11) + 10; // Entre 10 y 20 cajas
            getAlmacen().almacenarCajas(getNombre(), cantidad);
            try {
                Thread.sleep(new Random().nextInt(1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
