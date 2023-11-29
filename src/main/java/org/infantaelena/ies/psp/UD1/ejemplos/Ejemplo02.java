package org.infantaelena.ies.psp.UD1.ejemplos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo02 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Ejecutamos el proceso DIR
        Process p = new ProcessBuilder("CMD","/C","DIR").directory(new File("C:\\Users\\Deiv\\IdeaProjects\\DAM_2\\src\\main\\java\\org\\infantaelena\\ies\\psp\\UD1\\ejemplos")).start();
        
        //Mostramos caracter a caracer la salida del comando
        try {
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read())!=-1){
                System.out.print((char) c);
            }
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}

}
