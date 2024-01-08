package org.infantaelena.ies.psp.UD3.ejemplos.hilos_objetos_TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HilosObjetosTCPServidorAdivina {
	
public static void main(String[] arg)throws IOException {
		
		ServerSocket servidor = new ServerSocket(6001);
		System.out.println("Servidor iniciado...");
		
		//Número a adivinar entre 1 y 25
		int numero = (int) (1+25*Math.random());
		System.out.println("NÚMERO A ADIVINAR => "+numero);
		
		//todos los hilos comparte el objeto
		HilosObjetosTCPObjetoCompartido objeto = new HilosObjetosTCPObjetoCompartido(numero);
		int id = 0;
		while (true) {
			Socket cliente = new Socket();
			cliente = servidor.accept(); //esperando cliente
			id++;//identificador para el cliente
			HilosObjetosTCPHiloServidorAdivina hilo = new HilosObjetosTCPHiloServidorAdivina(cliente,objeto,id);
			hilo.start();
			
			}
		
	}

}
