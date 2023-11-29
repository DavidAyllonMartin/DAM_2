package org.infantaelena.ies.psp.UD2.practicas.hinchadoresGlobos;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private final List<Globo> globos = new ArrayList<>();
    private static final int CAPACIDAD_MAXIMA = 10;

    synchronized void dejarGlobo(Globo globo, String nombre) {
        while (globos.size() >= CAPACIDAD_MAXIMA) {
            try {
                System.out.println(nombre + " está esperando para dejar un globo " + globo.getColor() + " en la mesa.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        globos.add(globo);
        System.out.println(nombre + " dejó un globo " + globo.getColor() + " en la mesa.");
        notifyAll();
    }

    synchronized Globo cogerGlobo(String nombre) {
        while (globos.isEmpty()) {
            try {
                System.out.println(nombre + " está esperando para coger un globo de la mesa.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Globo globo = globos.remove(0);
        System.out.println(nombre + " cogió un globo " + globo.getColor() + " de la mesa.");
        notifyAll();
        return globo;
    }
}

