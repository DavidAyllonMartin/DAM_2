package org.infantaelena.ies.psp.UD1.ejerciciosRepaso;

import java.util.Arrays;
import java.util.Scanner;

public class EjerciciosRepaso1 {

    public static void main(String[] args) {

        ejercicio01();
        ejercicio02();
        ejercicio03();
        ejercicio03v2();

    }

    public static void ejercicio01() {
        //Leer 5 números y mostrarlos en el mismo orden introducido.

        int[] arrayInt = new int[5];
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce 5 números");
        for (int i = 0; i < 5; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            arrayInt[i] = sc.nextInt();
        }

        System.out.println("Los números que has introducido son los siguientes:");
        for (int i = 0; i < 5; i++) {
            System.out.print(arrayInt[i] + " ");
        }

    }

    public static void ejercicio02() {
        //Leer por teclado dos tablas de 10 números enteros y mezclarlas en una tercera de la forma: el 1º de A, el 1º de B, el 2º de A, el 2º de B, etc.

        Scanner sc = new Scanner(System.in);

        int[] tabla1 = new int[10];
        int[] tabla2 = new int[10];
        int[] tabla3 = new int[20];

        System.out.println("Introduce 10 números para la primera tabla");
        for (int i = 0; i < 10; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            tabla1[i] = sc.nextInt();
        }

        System.out.println("Introduce 10 números para la segunda tabla");
        for (int i = 0; i < 10; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            tabla2[i] = sc.nextInt();
        }

        for (int i = 0; i < 20; i += 2) {
            tabla3[i] = tabla1[i / 2];
            tabla3[i + 1] = tabla2[i / 2];
        }

        System.out.println("Estos son los números de las dos tablas intercalados:");
        for (int i : tabla3) {
            System.out.print(i + " ");
        }
    }

    public static void ejercicio03() {
        //Leer 5 elementos numéricos que se introducirán ordenados de forma creciente. Éstos los guardaremos en un array
        //de tamaño 10. Leer un número N, e insertarlo en el lugar adecuado para que el array continúe ordenado.

        Scanner sc = new Scanner(System.in);

        int[] arrayInt = new int[10];

        System.out.println("Introduce 5 números en orden creciente");
        for (int i = 0; i < 5; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            arrayInt[i] = sc.nextInt();
        }

        System.out.println("Introduce un número para insertarlo en la colección");
        System.out.print("Número: ");
        int num = sc.nextInt();
        int pos = 5;

        for (int i = 0; i < 5; i++) {
            if (num < arrayInt[i]) {
                pos = i;
                break;
            }
        }

        int aux;
        for (int i = pos; i < 6; i++) {

            aux = arrayInt[i];
            arrayInt[i] = num;
            num = aux;

        }

        System.out.println("Esta es tu colección de números ordenada");
        for (int i = 0; i < 6; i++) {
            System.out.print(arrayInt[i] + " ");
        }
    }

    public static void ejercicio03v2() {

        Scanner sc = new Scanner(System.in);

        int[] arrayInt = new int[6];

        System.out.println("Introduce 5 números");
        for (int i = 0; i < 5; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            arrayInt[i] = sc.nextInt();
        }

        System.out.println("Introduce un número para insertarlo en la colección");
        System.out.print("Número: ");
        arrayInt[5] = sc.nextInt();

        Arrays.sort(arrayInt);

        System.out.println("La colección de números ordenada");
        for (int i : arrayInt) {
            System.out.print(i + " ");
        }

    }

}
