package org.infantaelena.ies.psp.ejerciciosRepaso;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EjerciciosRepaso2 {

    public static void main(String[] args) {

        Alumno[] alumnos = introducirAlumnos();

        procesarAlumnos(alumnos);

    }

    private static int pedirNumeroAlumnos() {
        Scanner sc = new Scanner(System.in);
        int numeroAlumnos;
        try {
            numeroAlumnos = sc.nextInt();
            while (numeroAlumnos < 2) {
                System.out.println("El mínimo de alumnos a procesar es de 2. Por favor, introduce un número mayor o igual que 2.");
                numeroAlumnos = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Se ha producido un error. Por favor, introduzca un número entero.");
            numeroAlumnos = pedirNumeroAlumnos();
        }
        return numeroAlumnos;
    }

    private static String pedirNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre del alumno: ");

        return sc.nextLine();
    }

    private static double pedirNota() {
        Scanner sc = new Scanner(System.in);
        double nota;
        System.out.print("Nota del alumno: ");
        try {
            nota = sc.nextDouble();
            while (nota > 10 || nota < 0) {
                System.out.println("La nota  tiene que estar entre 0 y 10.");
                System.out.print("Nota del alumno: ");
                nota = sc.nextDouble();
            }

        } catch (InputMismatchException e) {
            System.out.println("Se ha producido un error. Por favor, introduce un número decimal.");
            nota = pedirNota();
        }
        return nota;
    }

    private static Alumno[] introducirAlumnos() {

        System.out.print("¿Cuántos alumnos quieres procesar?: ");

        int numeroAlumnos = pedirNumeroAlumnos();

        Alumno[] alumnos = new Alumno[numeroAlumnos];

        for (int i = 0; i < numeroAlumnos; i++) {
            String nombre = pedirNombre();
            double nota = pedirNota();

            alumnos[i] = new Alumno(nombre, nota);

        }
        return alumnos;
    }

    private static void procesarAlumnos(Alumno[] alumnos) {
        int numeroAlumnos = alumnos.length;
        double media = 0;
        String[] nombres = new String[numeroAlumnos];

        for (int i = 0; i < numeroAlumnos; i++) {
            nombres[i] = alumnos[i].getNombre();
            media += alumnos[i].getNota();
        }

        media = media / numeroAlumnos;

        System.out.println("La media de los " + numeroAlumnos + " alumnos es: " + media);

        StringBuilder strb = new StringBuilder("El nombre de los alumnos analizados es: ");

        for (int i = 0; i < numeroAlumnos; i++) {
            strb.append(nombres[i]);
            if (i != numeroAlumnos - 1) {
                strb.append(", ");
            } else {
                strb.append(".");
            }
        }

        System.out.println(strb);

    }

}

class Alumno {

    private String nombre;
    private double nota;

    public Alumno(String name, double mark) {
        setNombre(name);
        setNota(mark);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
