package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo14;

public class Cuenta {
	
	private int saldo;

	Cuenta(int s){
		saldo = s;
	}
	
	int getSaldo() {
		return saldo;
	}
	
	void restar(int cantidad) {
		saldo = saldo - cantidad;
	}
	
	synchronized void RetirarDinero(int cant, String nom) {
		if (getSaldo()>=cant) {
			System.out.println(nom + " : va a retirar saldo (el actual es: "+getSaldo()+" )");
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {}
			
			restar(cant);
			System.out.println("\t"+nom+" retira => "+cant+" Saldo Actual ( "+getSaldo()+" )");
		}else {
			System.out.println(nom + " No puede retirar la cantidad "+cant+"porque el saldo es: "+getSaldo() +" ");
		}
		if (getSaldo()<0) {
			System.out.println("SALDO NEGATIVO: "+getSaldo());
		}
		
	}

}
