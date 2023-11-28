package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.starWars.exceptions;

public class StarWarsCannotDeleteException extends Exception {
    public StarWarsCannotDeleteException() {
        super();
    }

    public StarWarsCannotDeleteException(String message) {
        super(message);
    }

    public StarWarsCannotDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarWarsCannotDeleteException(Throwable cause) {
        super(cause);
    }
}