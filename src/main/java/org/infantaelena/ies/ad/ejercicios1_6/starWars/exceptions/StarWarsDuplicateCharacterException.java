package org.infantaelena.ies.ad.ejercicios1_6.starWars.exceptions;

public class StarWarsDuplicateCharacterException extends Exception {
    public StarWarsDuplicateCharacterException() {
        super();
    }

    public StarWarsDuplicateCharacterException(String message) {
        super(message);
    }

    public StarWarsDuplicateCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarWarsDuplicateCharacterException(Throwable cause) {
        super(cause);
    }
}
