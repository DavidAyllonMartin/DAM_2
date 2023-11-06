/**Para mayor claridad, se envuelve la variable (a la que se hacía alusión antes) en la clase 
*SolicitaSuspender, en esta clase se define el método set() que da el valor true o false a la vable
*y llama al método notifyAll() , este notifica a todos los hilos que esperan(han ejecutado 
*un wait()) un cambio de estado sobre el objeto. En el método esperandoParaReanudar()
*se hace un wail() cuando el valor de la variable es true, el método wait() hace que el hilo espere
*hasta que le llegue un notify() o un notifyAll()
**/
package org.infantaelena.ies.psp.UD2.practicas.practica2_4;

public class SolicitaSuspender {
	private boolean suspender;
	
	public synchronized void set(boolean b) {
		suspender = b;//cambio de estado sobre el objeto
		notifyAll();
	}
	
	public synchronized void esperandoParaReanudar() throws InterruptedException{
		while(suspender)
			wait();//suspender hilo hasta recibir notify() o notifyAll()
		//met. wait() sólo puede ser llamado en un método synchronized (wait, notify y notifyAll 
				//se usan para sincronización de hilos. Pertenecen a Object y no parte de Thread.
	}
}