package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions;

public class StarWarsCannotReadException extends Exception {
    public StarWarsCannotReadException() {
        super();
    }

    public StarWarsCannotReadException(String message) {
        super(message);
    }

    public StarWarsCannotReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarWarsCannotReadException(Throwable cause) {
        super(cause);
    }
}