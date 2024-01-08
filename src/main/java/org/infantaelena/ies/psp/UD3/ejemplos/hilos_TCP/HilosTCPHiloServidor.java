package org.infantaelena.ies.psp.UD3.ejemplos.hilos_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HilosTCPHiloServidor extends Thread{
	
	//BufferedReader fEntrada;
	//PrintWriter fSalida;
	
	DataInputStream fEntrada;
	DataOutputStream fSalida;
	
	Socket socket = null;
	
	public HilosTCPHiloServidor (Socket s) throws IOException{
		socket = s;
		//SE CREAN FLUJOS DE ENTRADA Y DE SALIDA CON EL CLIENTE
		//fSalida = new PrintWriter(socket.getOutputStream(),true);
		//fEntrada = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		
		fSalida = new DataOutputStream (socket.getOutputStream());
		fEntrada = new DataInputStream (socket.getInputStream());
	}
	
	public void run (){
		String cadena = "";
		System.out.println("COMUNICO CON: "+ socket.toString());
		
		while(!cadena.trim().equals("*")) {
			try {
				//cadena = fEntrada.readLine();//obtener cadena
				//fSalida.println(cadena.trim().toUpperCase());//enviar mayúsculas
				
				cadena = fEntrada.readUTF();
				fSalida.writeUTF(cadena.trim().toUpperCase());
			}catch(IOException e) {}
		}
		
		System.out.println("FIN CON: "+socket.toString());
		
		try {
			fSalida.close();
			fEntrada.close();
			socket.close();
		}catch(IOException e) {}
	}


}
