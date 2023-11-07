package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo15;

class HiloCadena1 extends Thread {
	private ObjetoCompartido objeto;
    String cad;
	
    public HiloCadena1 (ObjetoCompartido c, String s) {		
    	this.objeto = c;
		this.cad=s;
	}
	
    public void run() {
    	//for (int j = 0; j < 10; j++) objeto.PintaCadena(cad);
        synchronized (objeto) {
        	for (int j = 0; j < 10; j++) { 
        		objeto.PintaCadena(cad);	
            }
        }//fin bloque synchronized
        System.out.print("\n"+cad + " finalizado");
	}//run

}//HiloCadena1
