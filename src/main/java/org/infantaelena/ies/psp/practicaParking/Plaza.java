package org.infantaelena.ies.psp.practicaParking;

/**
 * Clase que representa una plaza de parking en el sistema.
 *
 * Cada plaza tiene un identificador y un estado que indica si está ocupada por un coche o no.
 *
 * @author David Ayllón Martín
 * @version 1.0
 */
public class Plaza {
    /**
     * Identificador de la plaza.
     */
    private int idPlaza;

    /**
     * Indica si la plaza está ocupada por un coche o no.
     */
    private boolean ocupada;

    /**
     * Constructor de la clase Plaza.
     *
     * @param idPlaza Identificador de la plaza.
     */
    public Plaza(int idPlaza) {
        setIdPlaza(idPlaza);
        setOcupada(false);
    }

    /**
     * Método para obtener el identificador de la plaza.
     *
     * @return Identificador de la plaza.
     */
    public int getIdPlaza() {
        return idPlaza;
    }

    /**
     * Método para establecer el identificador de la plaza.
     *
     * @param idPlaza Identificador de la plaza.
     */
    public void setIdPlaza(int idPlaza) {
        this.idPlaza = idPlaza;
    }

    /**
     * Método para verificar si la plaza está ocupada por un coche.
     *
     * @return true si la plaza está ocupada, false de lo contrario.
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Método para establecer el estado de ocupación de la plaza por un coche.
     *
     * @param ocupada Indica si la plaza está ocupada por un coche.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
