package org.infantaelena.ies.psp.ejercicioFilosofos3;

public class Filosofo extends Thread{
	
	protected Palillo ind_izq, ind_der;
	protected int identidad;
	static final protected int MAX_DELAY=1000;
	
	public Filosofo(int id) {
		this.identidad=id;
		
		ind_izq=CenaFilosofos.mesa.palillo_izquierdo(id);
		ind_der=CenaFilosofos.mesa.palillo_derecho(id);
	}
	
	protected void pensar() {
		try {
				System.out.println("Filósofo "+identidad+" está pensando");
				Thread.sleep((int) (Math.random()*MAX_DELAY));
		}catch(InterruptedException e) {
			System.out.println("Filósofo "+identidad+" interrumpido");
			System.exit(1);
		}
	}
	
	protected void comer() {
		try {
				System.out.println("Filósofo "+identidad+" está comiendo");
				Thread.sleep((int) (Math.random()*MAX_DELAY));
		}catch (InterruptedException e) {
			System.out.println();
			System.exit(1);
		}
	}
	
	public void queriendo_comer() {
		System.out.println("Filósofo "+identidad+" quiere comer");
		ind_izq.coger();
		ind_der.coger();
	}
	
	public void dejando_de_comer() {

		ind_izq.soltar();
		ind_der.soltar();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			pensar();
			queriendo_comer();
			comer();
			dejando_de_comer();
		}
	}
	
	

}
