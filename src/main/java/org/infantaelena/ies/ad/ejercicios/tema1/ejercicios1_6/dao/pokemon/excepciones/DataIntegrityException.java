package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones;

public class DataIntegrityException extends Exception {
    public DataIntegrityException(){
        super();
    }
    public DataIntegrityException(String message){
        super(message);
    }
}
