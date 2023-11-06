package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo5;

public class EjemploRunnable implements Runnable{
	public void run() {
		System.out.println("Hola desde el Hilo! "+Thread.currentThread().getId());
	}

}
