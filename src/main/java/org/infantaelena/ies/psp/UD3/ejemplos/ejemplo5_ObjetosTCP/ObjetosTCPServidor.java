package org.infantaelena.ies.psp.UD3.ejemplos.ejemplo5_ObjetosTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjetosTCPServidor {
public static void main(String[] arg) throws IOException, ClassNotFoundException {
		
		int numeroPuerto = 60000;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
		System.out.println("Esperando al cliente...");
		Socket cliente = servidor.accept();
		
		//se prepara un flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream (cliente.getOutputStream());
		//se prepara un objeto y se envía
		ObjetosTCPPersona perEnvio = new ObjetosTCPPersona("Juan", 20);
		outObjeto.writeObject(perEnvio);
		System.out.println("Envío: "+perEnvio.getNombre()+"*"+perEnvio.getEdad());
		
		//se obtiene un stream para leer objetos
		ObjectInputStream inObjeto = new ObjectInputStream (cliente.getInputStream());
		//se prepara un objeto y se envía
		ObjetosTCPPersona perRecibo = (ObjetosTCPPersona) inObjeto.readObject();
		System.out.println("Recibido: "+perRecibo.getNombre()+"*"+perRecibo.getEdad());
		
		//CERRAR STREAMS Y SOCKETS
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
		
	}

}
