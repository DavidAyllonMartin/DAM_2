package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
/// No hacer nada evita la escritura de una nueva cabecera
    }
}
