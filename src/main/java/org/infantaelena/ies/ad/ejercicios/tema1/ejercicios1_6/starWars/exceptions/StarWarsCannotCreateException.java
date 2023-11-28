package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions;

public class StarWarsCannotCreateException extends Exception {
    public StarWarsCannotCreateException() {
        super();
    }

    public StarWarsCannotCreateException(String message) {
        super(message);
    }

    public StarWarsCannotCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarWarsCannotCreateException(Throwable cause) {
        super(cause);
    }
}
