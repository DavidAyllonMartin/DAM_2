package org.infantaelena.ies.psp.UD3.ejemplos.hilos_objetos_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HilosObjetosTCPHiloServidorAdivina extends Thread{
	
	ObjectInputStream fEntrada;
	ObjectOutputStream fSalida;
	Socket socket = null;
	HilosObjetosTCPObjetoCompartido objeto;
	int identificador;
	int intentos = 0;
	
	public HilosObjetosTCPHiloServidorAdivina(Socket s, HilosObjetosTCPObjetoCompartido objeto, int id) {
		this.socket = s;
		this.objeto = objeto;
		this.identificador = id;
		try {
			fSalida = new ObjectOutputStream(socket.getOutputStream());
			fEntrada = new ObjectInputStream (socket.getInputStream());
		}catch (IOException e) {
			System.out.println("ERROR DE E/S EN HiloServidor");
			e.printStackTrace();
		}
	}//constructor
	
	public void run() {
		System.out.println("=>Cliente conectado: "+identificador);
		
		//PREPARAR PRIMER ENVÍO AL CLIENTE
		HilosObjetosTCPDatos datos = new HilosObjetosTCPDatos ("Adivina un número entre 1 y 25", intentos, identificador);
		if(objeto.SeAcabo()) {
			datos.setCadena("LO SENTIMOS EL JUEGO HA TERMINADO, HAN ADIVINADO EL NÚMERO");
			datos.setJuega(false);//ya no tiene que jugar
		}
		try {
			fSalida.reset();
			fSalida.writeObject(datos);
		}catch(IOException e1) {
			System.out.println("Error en el Hilo al realizar el primer envío al jugador "+identificador);
			return;
		}
		
		while (!objeto.SeAcabo() || !(datos.getIntentos() == 5)) {
			int numecli = 0;
			try {
				//RECIBIR DATOS DEL CLIENTE
				HilosObjetosTCPDatos d = (HilosObjetosTCPDatos) fEntrada.readObject();
				numecli = Integer.parseInt(d.getCadena());
			}catch (IOException e) {
				System.out.println("Error en el Hilo al leer del jugador: "+identificador);
				break;
			}catch (NumberFormatException e) {
				System.out.println("El jugador: "+identificador+" se ha desconectado.");
				break;
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
			//JUEGA EL NÚMERO
			String cad = objeto.nuevaJugada(identificador, numecli);
			intentos++;
			
			datos = new HilosObjetosTCPDatos (cad,intentos,identificador);
			
			if (objeto.SeAcabo()) {
				datos.setJuega(false);//no tiene que seguir jugando
				if (identificador == objeto.getGanador())
					datos.setGana(true);
			}
			try {
				//ENVIAR DATOS AL CLIENTE
				fSalida.reset();
				fSalida.writeObject(datos);
			}catch (IOException n1) {
				System.out.println("Error escribiendo en flujo de salida del jugador: "+identificador+"*"+n1.getMessage());
				break;
			}catch(NullPointerException n) {
				System.out.println("El jugador "+identificador+" se ha desconectado.");
				break;
			}
			}//while
		if (objeto.SeAcabo()) {
			System.out.println("EL JUEGO SE HA ACABADO...");
			System.out.println("\t==>Desconecta: "+identificador);
		}
		try {
			fSalida.close();
			fEntrada.close();
			socket.close();
		}catch (IOException e) {
			System.out.println("Error en el Hilo al cerrar cliente: "+identificador);
			e.printStackTrace();
		}
		
		}//run
	

}
