package org.infantaelena.ies.psp.UD1.practica1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase permite al usuario ingresar dos números enteros, los suma y muestra el resultado.
 * @author David Ayllón Martín
 */
public class SumaNumeros {

    /**
     * Scanner utilizado para la entrada de datos desde el usuario.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * El método principal de la clase, que solicita dos números al usuario, los suma y muestra el resultado.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        System.out.println("Introduce el primer numero: ");
        int numero1 = pedirNumero();
        System.out.println("Introduce el segundo numero: ");
        int numero2 = pedirNumero();
        System.out.println("La suma de los dos numeros es: "+ (numero1 + numero2));

    }

    /**
     * Este método solicita al usuario un número entero y maneja las excepciones si se introduce un valor no válido.
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


