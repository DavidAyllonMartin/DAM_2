package org.infantaelena.ies.psp.UD3.ejemplos.ejemplo3_URL_connection;

/**
*
* @author ricardo
*/
import java.net.*;
import java.io.*;

public class Ejemplo1URLCon {
 public static void main(String[] args) {
	URL url=null;
	URLConnection urlCon=null;
	try {
		url = new URL("http://problemastenemostodos.com/");		
		urlCon= url.openConnection();
		
		BufferedReader in;
		InputStream inputStream = urlCon.getInputStream();
		in = new BufferedReader(new 
                              InputStreamReader(inputStream));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		
		in.close();
	}
	catch (MalformedURLException e) {e.printStackTrace();} 
	catch (IOException e) {e.printStackTrace();}
 }//
}//Ejemplo1urlCon