package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones;

public class IncompatibleVersionException extends Exception {
    public IncompatibleVersionException(){
        super();
    }
    public IncompatibleVersionException(String message){
        super(message);
    }
}
