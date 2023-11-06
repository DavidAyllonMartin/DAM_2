/**Para suspender de forma segura el hilo se debe introducir en el hilo una vble, por ej, suspender
*y comprobar su valor dentro del método run(), es lo que se hace en la llamada al 
*método esperandoParaReanudar() de este código.
*El método Suspende del hilo da valor true a la variable para suspender el hilo.
*El método Reanuda da el valor false para que detenga la suspensión y continue
*ejecutándose el hilo.
**/
package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo7;

public class MyHilo extends Thread {
	
	private SolicitaSuspender suspender = new SolicitaSuspender();
	
	public void Suspende() {
		suspender.set(true);//petición de suspender hilo
	}
	public void Reanuda() {
		suspender.set(false);//petición de continuar
	}
	
	public void run (){
		try {
			while (true) {
				suspender.esperandoParaReanudar();//comprobar
			}
		}catch (InterruptedException exception) {}
	}

}