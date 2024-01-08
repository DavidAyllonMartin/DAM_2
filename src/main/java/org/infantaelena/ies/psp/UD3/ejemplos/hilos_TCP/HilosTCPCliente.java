package org.infantaelena.ies.psp.UD3.ejemplos.hilos_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HilosTCPCliente {
	
	public static void main(String[] arg) throws IOException{
		String host="localhost";
		int puerto = 60000;
		Socket Cliente = new Socket(host,puerto);
		
		//SE CREAN LOS FLUJOS DE ENTRADA Y SALIDA
		//PrintWriter fSalida = new PrintWriter(Cliente.getOutputStream(),true);
		//BufferedReader fEntrada = new BufferedReader (new InputStreamReader(Cliente.getInputStream()));
		
		DataOutputStream fSalida = new DataOutputStream (Cliente.getOutputStream());
		DataInputStream fEntrada = new DataInputStream (Cliente.getInputStream());
		
		
		//FLUJO PARA ENTRADA ESTANDAR
		//BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		
		Scanner in = new Scanner(System.in);
		
		String cadena, eco ="";
		do {
			System.out.print("Introduce cadena: ");
			//cadena = in.readLine();
			//fSalida.println(cadena);//envío de cadena al servidor
			//eco = fEntrada.readLine();
			
			cadena = in.nextLine();
			fSalida.writeUTF(cadena);
			eco = fEntrada.readUTF();
			System.out.println(" =>ECO: " + eco);  
		}while (!cadena.trim().contentEquals("*"));
		
		fSalida.close();
		fEntrada.close();
		System.out.println("Fin del envío.");
		in.close();
		Cliente.close();
		
	}

}
