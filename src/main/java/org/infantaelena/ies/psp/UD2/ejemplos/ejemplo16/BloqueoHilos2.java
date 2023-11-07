package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo16;

public class BloqueoHilos2 {
	public static void main(String[] args) {
		ObjetoCompartido com = new ObjetoCompartido();
		HiloCadena2  a = new HiloCadena2 (com, " A ");
		HiloCadena2  b = new HiloCadena2 (com, " B ");
		a.start();
		b.start();
	}
}//BloqueoHilos
