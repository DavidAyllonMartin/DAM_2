package org.infantaelena.ies.psp.ejercicioFilosofos3;

public class CenaFilosofos {
	
	public static MesaCircular mesa;
	
	public static void main(String[] args) throws InterruptedException{
		
		int filosofos= Integer.parseInt(args[0]);
		mesa=new MesaCircular(filosofos);
		System.out.println("Sentados "+filosofos+" filósofos");
		
		for (int i = 0; i < filosofos; i++) {
			Filosofo f=new Filosofo(i);
			f.start();
			
		}
	}

}