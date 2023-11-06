package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo5;

public class EjemploRunnableb {
	public static void main(String[] args) {
		//Primer hilo
		EjemploRunnable hilo1 = new EjemploRunnable();
		new Thread(hilo1).start();
		
		//Segundo hilo
		EjemploRunnable hilo2 = new EjemploRunnable();
		Thread hilo = new Thread(hilo2);
		hilo.start();
		
		//Tercer hilo
		new Thread(new EjemploRunnable()).start();
	}

}
