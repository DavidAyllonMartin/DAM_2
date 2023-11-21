package org.infantaelena.ies.psp.ejercicioFilosofos3;

public class Palillo {
	
	private int numero;
	private boolean cogido;
	
	public Palillo(int id) {
		this.numero=id;
		this.cogido=false;
	}
	
	public int getId() {
		return this.numero;
	}
	
	synchronized public void coger() {
		
		while (cogido) {
			try {
				System.out.println("Palillo "+numero+" bloqueado");
				wait();
			}catch(InterruptedException e) {
				System.out.println("-Espera por palillo "+numero+" interrrumpida");
				System.exit(1);
			}
		}
		cogido=true;
		System.out.println("Palillo "+numero+" ha sido cogido");
	}
	
	synchronized public void soltar() {
		
		cogido=false;
		System.out.println("Palillo "+numero+" ha sido soltado");
		notify();
	}

}
