package org.infantaelena.ies.psp.UD1.ejemplos;

import java.io.*;

public class Ejemplo04 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
Process p = new ProcessBuilder("CMD","/C","DATE").start();
//escritura
OutputStream os = p.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("15-06-18");
        bw.flush();
//os.write("15-06-18".getBytes());
//os.flush();
//lectura
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
//comprobaci�n de error - 0 bien - 1 mal
int exitVal;
try {
    exitVal = p.waitFor();
    System.out.println("Valor de salida: " + exitVal);
}
catch (Exception e){
    e.printStackTrace();
}
	}

}
