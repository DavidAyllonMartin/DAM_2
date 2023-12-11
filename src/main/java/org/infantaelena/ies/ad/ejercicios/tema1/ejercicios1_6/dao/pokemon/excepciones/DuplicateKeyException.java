package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones;

public class DuplicateKeyException extends Exception {
    public DuplicateKeyException(){
        super();
    }
    public DuplicateKeyException(String message){
        super(message);
    }
}
