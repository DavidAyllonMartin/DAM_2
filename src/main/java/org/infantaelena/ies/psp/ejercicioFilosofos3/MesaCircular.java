package org.infantaelena.ies.psp.ejercicioFilosofos3;

public class MesaCircular {
	
	private Palillo palillos[];
	private int filosofos;
	
	public MesaCircular(int personas) {
		this.filosofos=personas;
		palillos=new Palillo[personas];
		for (int i = 0; i < personas; i++) {
			palillos[i]=new Palillo(i);
		}
	}
	
	public Palillo palillo_derecho(int i) {
		return palillos[(i+1)%filosofos];
	}
	
	public Palillo palillo_izquierdo(int i) {
		return palillos[i];
	}

}
