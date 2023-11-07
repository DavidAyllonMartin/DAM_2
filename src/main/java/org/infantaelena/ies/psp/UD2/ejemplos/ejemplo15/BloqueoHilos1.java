package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo15;

public class BloqueoHilos1 {
	public static void main(String[] args) {
		ObjetoCompartido com = new ObjetoCompartido();
		HiloCadena1 a = new HiloCadena1(com, " A ");
		HiloCadena1 b = new HiloCadena1(com, " B ");
		a.start();
		b.start();
	}
}//BloqueoHilosInicial