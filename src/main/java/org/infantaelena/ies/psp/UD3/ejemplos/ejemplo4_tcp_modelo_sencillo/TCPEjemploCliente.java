package org.infantaelena.ies.psp.UD3.ejemplos.ejemplo4_tcp_modelo_sencillo;

/**
*
* @author ricardo
*/
import java.io.*;
import java.net.*;

public class TCPEjemploCliente  {
 public static void main(String[] args) throws Exception {
	String Host = "localhost";
	int Puerto = 6000;//puerto remoto	
	
	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);

	// CREO FLUJO DE SALIDA AL SERVIDOR	
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

	// ENVIO UN SALUDO AL SERVIDOR
	flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

	// CREO FLUJO DE ENTRADA AL SERVIDOR	
	DataInputStream flujoEntrada = new 
           DataInputStream(Cliente.getInputStream());

	// EL SERVIDOR ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();	
	flujoSalida.close();	
	Cliente.close();	
 }// main
}// 
