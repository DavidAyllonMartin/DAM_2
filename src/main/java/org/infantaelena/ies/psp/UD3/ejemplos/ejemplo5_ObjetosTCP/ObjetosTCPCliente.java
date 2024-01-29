package org.infantaelena.ies.psp.UD3.ejemplos.ejemplo5_ObjetosTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjetosTCPCliente {
public static void main (String[] arg) throws IOException, ClassNotFoundException{
		
		String Host="localhost";
		int Puerto = 60000;
		
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		Socket cliente =  new Socket(Host, Puerto);
		
		//flujo de entrada para objetos
		ObjectInputStream inObjeto = new ObjectInputStream (cliente.getInputStream());
		//se recibe un objeto
		ObjetosTCPPersona persona = (ObjetosTCPPersona) inObjeto.readObject();
		System.out.println("Recibo: "+persona.getNombre()+"*"+persona.getEdad());
		
		//se modifica el objeto
		persona.setNombre("Juan Ramos");
		persona.setEdad(22);
		
		//flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		//se envía el objeto modificado
		outObjeto.writeObject(persona);
		System.out.println("Envío: "+persona.getNombre()+"*"+persona.getEdad());
		
		//CERRAR STREAMS Y SOCKETS
		inObjeto.close();
		outObjeto.close();
		cliente.close();
		
	}

}
