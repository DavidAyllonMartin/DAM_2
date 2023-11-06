package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo10;

import static java.lang.Thread.sleep;

class HiloJoin extends Thread {
	private int n;
	
	public HiloJoin(String nombre, int n) {
		super(nombre);  
		this.n=n;	
	}
  
	public void run() {
		for(int i=1; i<= n; i++)  {
			System.out.println(getName() + ": " + i);
			try {
				sleep(1000); 
			} catch (InterruptedException ignore) {}    	   
		}
		System.out.println("Fin Bucle "+getName());
	}//run
 }//clase