package org.infantaelena.ies.psp.UD3.ejemplos.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServidor {
	
	public static void main(String[] argv) throws Exception {
	    byte[] bufer = new byte[1024];//bufer para recibir el datagrama 
	    //ASOCIO EL SOCKET AL PUERTO 12345
	    DatagramSocket socket = new DatagramSocket(12345);   

	    //ESPERANDO DATAGRAMA
	    System.out.println("Esperando Datagrama .......... ");  
	    DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
	    socket.receive(recibo);//recibo datagrama
	    int bytesRec = recibo.getLength();//obtengo numero de bytes
	    String paquete= new String(recibo.getData());//obtengo String

	    //VISUALIZO INFORMACION
	    System.out.println("Número de Bytes recibidos: " + bytesRec);    
	    System.out.println("Contenido del Paquete    : " + paquete.trim());
	    System.out.println("Puerto origen del mensaje: " + recibo.getPort());
	    System.out.println("IP de origen             : " + recibo.getAddress().getHostAddress());   
	    System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());
	    
	    // DIRECCION ORIGEN DEL MENSAJE
	    InetAddress IPOrigen = recibo.getAddress();
	    int puerto = recibo.getPort();

	    // ENVIANDO DATAGRAMA AL CLIENTE
	    byte[] mensaje = new byte[1024];   
	    String Saludo="Devuelvo Saludos !!";
	    mensaje = Saludo.getBytes();  //codifico String a bytes
	    
	    //CONSTRUYO EL DATAGRAMA A ENVIAR al cliente
	    DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, IPOrigen, puerto);
	    
	    System.out.println("Enviando Datagrama de longitud: "+ mensaje.length);
	    System.out.println("Host destino : "+  IPOrigen.getHostName());
	    System.out.println("IP Destino   : " + IPOrigen.getHostAddress());
	    System.out.println("Puerto local del socket: " + socket.getLocalPort());
	    System.out.println("Puerto al que envio: " + envio.getPort());

	    //ENVIO DATAGRAMA	
	    socket.send(envio);
	    
	    socket.close(); //cierro el socket
	  }

}
