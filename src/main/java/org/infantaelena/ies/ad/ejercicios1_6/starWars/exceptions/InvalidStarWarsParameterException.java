package org.infantaelena.ies.ad.ejercicios1_6.starWars.exceptions;

public class InvalidStarWarsParameterException extends Exception {
    public InvalidStarWarsParameterException() {
        super();
    }

    public InvalidStarWarsParameterException(String message) {
        super(message);
    }

    public InvalidStarWarsParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStarWarsParameterException(Throwable cause) {
        super(cause);
    }
}
