package org.infantaelena.ies.psp.UD2.practicas.practica2_1;

public class Main {

    public static void main(String[] args) {
        TicThread tic = new TicThread();
        TacThread tac = new TacThread();

        tic.start();
        tac.start();

        // No hay control en el orden de ejecución, así que el orden puede variar en función de cómo se destinen los
        // recursos en el procesador
    }




}
