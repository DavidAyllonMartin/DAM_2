package org.infantaelena.ies.psp.UD3.ejemplos.hilos_TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HilosTCPServidor {
	
	public static void main (String[] arg) throws IOException{
		ServerSocket servidor;
		servidor = new ServerSocket(60000);
		System.out.println("Servidor iniciado...");
		
		while (true) {
			Socket cliente = new Socket();
			cliente = servidor.accept();//esperando al cliente
			HilosTCPHiloServidor hilo = new HilosTCPHiloServidor(cliente);
			hilo.start();//se atiende al cliente
		}
	}


}
