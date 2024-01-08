package org.infantaelena.ies.psp.UD3.ejemplos.UDP_multicast;

/**
*
* @author ricardo
*/


import java.net.*; 

public class UDPEjemploReceptorMulticast { 
 public static void main(String args[]) throws Exception { 
   System.setProperty("java.net.preferIPv4Stack", "true");//por si alguien utiliza MacOS
   //Se crea el socket multicast 
   int puerto = 12345;//Puerto multicast
   MulticastSocket ms = new MulticastSocket(puerto);  
  
   InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo

   //Nos unimos al grupo
   ms.joinGroup(grupo);

   String msg="";
   
   //
   while(!msg.trim().equals("*")) {  
     byte[] buf = new byte[1024];
     //Recibe el paquete del servidor multicast      
     DatagramPacket paquete = new DatagramPacket(buf, buf.length);
     ms.receive(paquete);
     msg = new String(paquete.getData());
     System.out.println ("Recibo: " + msg.trim());	
   }
   ms.leaveGroup (grupo); //abandonamos grupo
   ms.close (); //cierra socket
   System.out.println ("Socket cerrado...");
 } 
} 

