/**Para suspender de forma segura el hilo se debe introducir en el hilo una vble, por ej, suspender
*y comprobar su valor dentro del método run(), es lo que se hace en la llamada al 
*método esperandoParaReanudar() de este código.
*El método Suspende del hilo da valor true a la variable para suspender el hilo.
*El método Reanuda da el valor false para que detenga la suspensión y continue
*ejecutándose el hilo.
**/
package org.infantaelena.ies.psp.UD2.practicas.practica2_4;

public class MyHilo extends Thread {
	private int contador = 0;
	private SolicitaSuspender suspender = new SolicitaSuspender();

	public int getContador(){
		return this.contador;
	}

	public void setContador(int contador){
		this.contador = contador;
	}

	public void Suspende() {
		suspender.set(true);//petición de suspender hilo
	}

	public void Reanuda() {
		suspender.set(false);//petición de continuar
	}

	public void run (){
		boolean continuarBucle = true;
		try {
			while (continuarBucle) {
				setContador(getContador() + 1);
				System.out.println(getContador());
				Thread.sleep(1000);
				suspender.esperandoParaReanudar();//comprobar
			}
			System.out.println("Hilo terminado");
		}catch (InterruptedException ignored) {
			//Ignorado
		}
	}

}