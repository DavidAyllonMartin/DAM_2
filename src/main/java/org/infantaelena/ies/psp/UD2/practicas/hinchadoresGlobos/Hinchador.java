package org.infantaelena.ies.psp.UD2.practicas.hinchadoresGlobos;

import java.util.Random;

public class Hinchador extends Thread {
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

    Hinchador(String nombre, Mesa mesa) {
        setMesa(mesa);
        setNombre(nombre);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Color color = Color.values()[random.nextInt(Color.values().length)];
            Globo globo = new Globo(color);

            mesa.dejarGlobo(globo, getNombre());

            try {
                System.out.println(getNombre() + " descansa un rato para recuperar el aliento.");
                Thread.sleep(random.nextInt(1000) + 1000); // Descansar entre 1 y 2 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

