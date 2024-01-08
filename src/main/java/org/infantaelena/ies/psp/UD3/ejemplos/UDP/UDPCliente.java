package org.infantaelena.ies.psp.UD3.ejemplos.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCliente {
	
	public static void main(String[] argv) throws Exception {
	    InetAddress destino = InetAddress.getLocalHost();
	    System.out.println("Variable destino: "+destino);
	    int port = 12345; //puerto al que envio el datagrama
	    byte[] mensaje = new byte[1024];   
	    
	    String Saludo="Enviando Saludos !!";
	    mensaje = Saludo.getBytes();  //codifico String a bytes
	    
	    //CONSTRUYO EL DATAGRAMA A ENVIAR
	    DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
	    DatagramSocket socket = new DatagramSocket(34567);//Puerto local
	    
	    System.out.println("Enviando Datagrama de longitud: "+ mensaje.length);
	    System.out.println("Host destino : "+  destino.getHostName());
	    System.out.println("IP Destino   : " + destino.getHostAddress());
	    System.out.println("Puerto local del socket: " + socket.getLocalPort());
	    System.out.println("Puerto al que envio: " + envio.getPort());

	    //ENVIO DATAGRAMA	
	    socket.send(envio);
	        
	    // RECIBIENDO DATAGRAMA DEL SERVIDOR
	    byte[] recibidos = new byte[1024];
	    DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
	    System.out.println("Esperando datagrama....");
	    socket.receive(recibo);
	    int bytesRec = recibo.getLength();
	    String paquete= new String(recibo.getData());
	    System.out.println("Número de Bytes recibidos: "+ bytesRec);    
	    System.out.println("Contenido del Paquete    : "+ paquete.trim());
	    System.out.println("Puerto origen del mensaje: "+ recibo.getPort());
	    System.out.println("IP de origen             : "+ recibo.getAddress().getHostAddress());   
	    System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());
	  
	    socket.close(); //cierro el socket
	  }

}
