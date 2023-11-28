package org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_1;

import java.io.File;
import java.net.URI;

//Crea una clase que herede de java.io.File y le añada un método que se llame showInfo(). Este método devolverá un
//String con la siguiente información del fichero:
//Nombre.
//Ruta.
//Ruta absoluta.
//¿Se puede leer?
//¿Se puede escribir?
//Tamaño.
//¿Es un directorio? En caso afirmativo mostrará los contenidos del mismo.
//¿Es un fichero?
//Cada elemento debe ir en una línea y llevar delante un texto que indique a qué
//se refiere.
class ShowInfo extends File {

    public ShowInfo(String pathname) {
        super(pathname);
    }

    public ShowInfo(String parent, String child) {
        super(parent, child);
    }

    public ShowInfo(File parent, String child) {
        super(parent, child);
    }

    public ShowInfo(URI uri) {
        super(uri);
    }

    public String showInfo() {
        StringBuilder strb = new StringBuilder();
        strb.append("Nombre: ").append(getName()).append("\n");
        strb.append("Ruta: ").append(getPath()).append("\n");
        strb.append("Ruta Absoluta: ").append(getAbsolutePath()).append("\n");
        strb.append("¿Se puede leer? ").append(canRead()).append("\n");
        strb.append("¿Se puede escribir? ").append(canWrite()).append("\n");

        if (isFile()) {
            strb.append("Tamaño: ").append(length()).append(" bytes").append("\n");
            strb.append("¿Es un directorio? No").append("\n");
            strb.append("¿Es un fichero? Sí").append("\n");
        } else if (isDirectory()) {
            strb.append("¿Es un directorio? Sí").append("\n");
            strb.append("¿Es un fichero? No").append("\n");

            File[] ficheros = listFiles();
            if (ficheros != null && ficheros.length > 0) {
                strb.append("Contenidos del directorio:").append("\n");
                for (File fichero : ficheros) {
                    strb.append("  - ").append(fichero.getName()).append("\n");
                }
            }
        } else {
            strb.append("No es ni un directorio ni un fichero.").append("\n");
        }

        return strb.toString();
    }
}
