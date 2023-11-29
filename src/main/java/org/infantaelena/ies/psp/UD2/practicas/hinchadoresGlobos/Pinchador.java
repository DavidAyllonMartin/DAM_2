package org.infantaelena.ies.psp.UD2.practicas.hinchadoresGlobos;

import java.util.Random;

public class Pinchador extends Thread {
    private Mesa mesa;
    private String nombre;

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    Pinchador(String nombre, Mesa mesa) {
        setNombre(nombre);
        setMesa(mesa);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Globo globo = mesa.cogerGlobo(getNombre());

            try {
                System.out.println(getNombre() + " descansa un rato para recuperarse del susto.");
                Thread.sleep(random.nextInt(1000) + 1000); // Descansar entre 1 y 2 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

