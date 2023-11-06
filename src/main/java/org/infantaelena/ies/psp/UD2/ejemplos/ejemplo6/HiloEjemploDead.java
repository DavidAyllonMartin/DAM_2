package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo6;


public class HiloEjemploDead extends Thread {
  private boolean stopHilo= false; 
  public void pararHilo()  {
    stopHilo = true;
  }  
  
  
  //metodo run
  public void run() {
	while (!stopHilo) {
	  System.out.println("Inicio del bucle del hilo y hago mis cositas");
          
          //en este bloque de código es donde estarían las acciones que no pueden ser interrumpidas
          try {
              sleep(3000);
          } catch (Exception e) {}
          
          
          System.out.println("Fin del bucle del hilo... aquí ya habríamos terminado de hacer lo necesario y podríamos parar");
	}
  }
  
  
  public static void main(String[] args) {
	HiloEjemploDead h = new HiloEjemploDead ();
        h.start();
	for(int i=0;i<2000000000; i++) ;//no hago nada	
	h.pararHilo();
  }// main   
 }//fin clase hilo