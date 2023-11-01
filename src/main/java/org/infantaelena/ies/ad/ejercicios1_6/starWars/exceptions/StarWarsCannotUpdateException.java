package org.infantaelena.ies.ad.ejercicios1_6.starWars.exceptions;

public class StarWarsCannotUpdateException extends Exception {
    public StarWarsCannotUpdateException() {
        super();
    }

    public StarWarsCannotUpdateException(String message) {
        super(message);
    }

    public StarWarsCannotUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarWarsCannotUpdateException(Throwable cause) {
        super(cause);
    }
}
