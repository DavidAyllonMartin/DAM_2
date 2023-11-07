package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo16;

class HiloCadena2 extends Thread {
	private ObjetoCompartido objeto;
    String cad;
	public HiloCadena2 (ObjetoCompartido c, String s) {		
		this.objeto = c;
		this.cad=s;
	}
	public void run() {
		synchronized (objeto) {
			for (int j = 0; j < 10; j++) {				
				objeto.PintaCadena(cad);
				objeto.notify(); //aviso que ya he usado el objeto
				try {
					objeto.wait();//esperar a que llegue un notify 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}//for 
		    objeto.notify(); //despertar a todos los wait sobre el objeto
		}//fin bloque synchronized

		  //System.out.print("\n"+cad + " finalizado");
	}//run

}//HiloCadena
