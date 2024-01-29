package org.infantaelena.ies.psp.UD3.ejemplos.ejemplo7_hilos_objetos_TCP;

public class HilosObjetosTCPObjetoCompartido {
	
	private int numero; //numero a adivinar
	private boolean acabo; //true cuando se haya terminado el juego
	private int ganador; //jugador ganador
	
	public HilosObjetosTCPObjetoCompartido(int numero) {
		this.numero = numero; //numero a adivinar
		this.acabo = false;
	}
	
	public boolean SeAcabo () {return acabo;}
	
	public int getGanador() {return ganador;}
	
	public synchronized String nuevaJugada(int jugador, int suNumero) {
		String cad = "";
		if (!SeAcabo()) {
			if (suNumero > numero) {//grande
				cad = "Número demasiado grande";
			}
			if (suNumero < numero) {//pequeño
				cad = "Número demasiado pequeño";
			}
			if (suNumero == numero) {//se ha acertado
				cad = "Jugador "+jugador+"gana, adivinó el número: "+numero;
				acabo = true;
				ganador = jugador;
			}
			
		}else {
			cad = "Jugador "+ganador+" adivinó el número: "+numero;
		}
		return cad;
	}

}
