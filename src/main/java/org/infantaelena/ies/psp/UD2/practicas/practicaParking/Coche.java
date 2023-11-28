package org.infantaelena.ies.psp.UD2.practicas.practicaParking;

import java.util.Random;

/**
 * Clase que representa un coche en el sistema de parking.
 *
 * Cada coche tiene un identificador, un estado que indica si tiene permiso para pasar,
 * una referencia al parking al que pertenece y la plaza de parking asignada.
 *
 * Extiende la clase Thread para permitir la ejecución concurrente de las operaciones de entrada
 * y salida del parking.
 *
 * @author David Ayllón Martín
 * @version 1.0
 */
public class Coche extends Thread {
    /**
     * Tiempo mínimo de espera en segundos entre operaciones de entrada y salida del parking.
     */
    public static final int TIEMPO_MINIMO = 5;

    /**
     * Tiempo máximo de espera en segundos entre operaciones de entrada y salida del parking.
     */
    public static final int TIEMPO_MAXIMO = 10;

    /**
     * Identificador del coche.
     */
    private int idCoche;

    /**
     * Indica si el coche tiene permiso para pasar.
     */
    private boolean permisoPaso;

    /**
     * Referencia al parking al que pertenece el coche.
     */
    private Parking parking;

    /**
     * Plaza de parking asignada al coche.
     */
    private Plaza plazaAsignada;

    /**
     * Constructor de la clase Coche.
     *
     * @param idCoche Identificador del coche.
     * @param parking Referencia al parking al que pertenece el coche.
     */
    public Coche(int idCoche, Parking parking) {
        setIdCoche(idCoche);
        setPermisoPaso(false);
        setParking(parking);
        setPlazaAsignada(null);
    }

    /**
     * Método para obtener el identificador del coche.
     *
     * @return Identificador del coche.
     */
    public int getIdCoche() {
        return idCoche;
    }

    /**
     * Método para establecer el identificador del coche.
     *
     * @param idCoche Identificador del coche.
     */
    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    /**
     * Método para verificar si el coche tiene permiso para pasar.
     *
     * @return true si el coche tiene permiso para pasar, false de lo contrario.
     */
    public boolean tienePermisoDePaso() {
        return permisoPaso;
    }

    /**
     * Método para establecer el estado de permiso de paso del coche.
     *
     * @param permisoPaso Indica si el coche tiene permiso para pasar.
     */
    public void setPermisoPaso(boolean permisoPaso) {
        this.permisoPaso = permisoPaso;
    }

    /**
     * Método para obtener la referencia al parking al que pertenece el coche.
     *
     * @return Referencia al parking al que pertenece el coche.
     */
    public Parking getParking() {
        return parking;
    }

    /**
     * Método para establecer la referencia al parking al que pertenece el coche.
     *
     * @param parking Referencia al parking al que pertenece el coche.
     * @throws IllegalArgumentException Si el parking es nulo.
     */
    public void setParking(Parking parking) {
        if (parking == null) {
            throw new IllegalArgumentException("El parking de un coche no puede ser un valor nulo");
        }
        this.parking = parking;
    }

    /**
     * Método para obtener la plaza de parking asignada al coche.
     *
     * @return Plaza de parking asignada al coche.
     */
    public Plaza getPlazaAsignada() {
        return plazaAsignada;
    }

    /**
     * Método para establecer la plaza de parking asignada al coche.
     *
     * @param plazaAsignada Plaza de parking asignada al coche.
     */
    public void setPlazaAsignada(Plaza plazaAsignada) {
        this.plazaAsignada = plazaAsignada;
    }

    /**
     * Método que representa el comportamiento concurrente del coche. Realiza operaciones
     * de entrada y salida del parking de manera continua.
     */
    @Override
    public void run() {
        while (true) {
            parking.entradaAlParking(this);
            esperar();
            parking.salidaDelParking(this);
            esperar();
        }
    }

    /**
     * Método privado que hace que el hilo espere un tiempo aleatorio entre {@code TIEMPO_MINIMO} y
     * {@code TIEMPO_MAXIMO} segundos.
     */
    private void esperar() {
        int tiempoEspera = generarNumeroAleatorioEnRango();
        try {
            Thread.sleep(tiempoEspera * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método privado que genera un número aleatorio en el rango [TIEMPO_MINIMO, TIEMPO_MAXIMO].
     *
     * @return Número aleatorio en el rango [TIEMPO_MINIMO, TIEMPO_MAXIMO].
     */
    private int generarNumeroAleatorioEnRango() {
        int rango = TIEMPO_MAXIMO - TIEMPO_MINIMO;
        Random random = new Random();
        return random.nextInt(rango + 1) + TIEMPO_MINIMO;
    }
}
