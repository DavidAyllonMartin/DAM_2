package org.infantaelena.ies.psp.UD1.practica1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase se utiliza para compilar y ejecutar el programa SumaNumeros desde Java.
 * El programa SumaNumeros es un programa que solicita dos números enteros al usuario,
 * los suma y muestra el resultado.
 *
 * Requiere que el archivo SumaNumeros.java esté presente en el directorio "src" y que
 * sea compilado antes de su ejecución.
 * @author David Ayllón Martín
 */
public class EjecutarSuma {

    /**
     * Scanner utilizado para la entrada de datos desde el usuario.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * El método principal de la clase, que realiza la compilación y ejecución del programa SumaNumeros.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        try {
            File directorio = new File("src");

            // Compilamos el archivo SumaNumeros.java antes de ejecutarlo
            ProcessBuilder compileProcess = new ProcessBuilder("javac", "SumaNumeros.java");
            compileProcess.directory(directorio);
            Process compile = compileProcess.start();
            compile.waitFor();

            // Verificamos si ha compilado
            if (compile.exitValue() == 0) {

                // Ahora ejecutamos el programa SumaNumeros
                ProcessBuilder pb = new ProcessBuilder("java", "SumaNumeros");
                pb.directory(directorio);

                // Ejecutamos el proceso
                Process p = pb.start();

                // Obtenemos el flujo de salida del proceso
                OutputStream os = p.getOutputStream();

                // Introducción de datos en el flujo de salida
                int numero1 = pedirNumero();
                int numero2 = pedirNumero();
                String num1 = numero1+"\n";
                String num2 = numero2+"\n";

                os.write(num1.getBytes());
                os.write(num2.getBytes());
                os.flush();

                // Respuesta de SumaNumeros
                InputStream is = p.getInputStream();
                int c;
                while ((c = is.read()) != -1)
                    System.out.print((char) c);

                is.close();
                os.close();

                // COMPROBACION DE ERROR - 0 bien - 1 mal
                int exitVal;
                try {
                    exitVal = p.waitFor();
                    System.out.println("Valor de Salida: " + exitVal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    InputStream er = p.getErrorStream();
                    BufferedReader brer = new BufferedReader(new InputStreamReader(er));
                    String liner = null;
                    while ((liner = brer.readLine()) != null)
                        System.out.println("ERROR >" + liner);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } else {
                System.out.println("Error de compilación");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método solicita al usuario un número entero y maneja las excepciones si se introduce
     * un valor no válido.
     *
     * @return El número entero ingresado por el usuario.
     */
    private static int pedirNumero() {
        int numero;
        try {
            numero = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Se ha producido un error. Por favor, introduce un número entero.");
            //Limpiamos el scanner en caso de que se haya introducido un String
            sc.next();
            numero = pedirNumero();
        }
        return numero;
    }
}
