package org.infantaelena.ies.psp.practicaParking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que representa un sistema de parking con plazas y coches.
 *
 * El sistema permite la entrada y salida de coches del parking, gestionando las plazas disponibles
 * y una cola de espera en caso de que todas las plazas estén ocupadas.
 *
 * @author David Ayllón Martín
 * @version 1.0
 */
public class Parking {
    /**
     * Número total de plazas disponibles en el parking.
     */
    private int numeroPlazas;

    /**
     * Número de plazas actualmente ocupadas en el parking.
     */
    private int plazasOcupadas;

    /**
     * Array de objetos Plaza que representan las plazas individuales del parking.
     */
    private Plaza[] plazas;

    /**
     * Array de objetos Coche que representan los coches en el parking.
     */
    private Coche[] coches;

    /**
     * Cola de espera para coches cuando todas las plazas están ocupadas.
     */
    private Queue<Coche> cola;

    /**
     * Constructor de la clase Parking que inicializa el sistema con un número específico de plazas
     * y coches. Crea las plazas y coches correspondientes.
     *
     * @param numeroPlazas Número total de plazas disponibles en el parking.
     * @param numeroCoches Número de coches que quieren acceder al parking.
     * @throws IllegalArgumentException Si el número de coches y/o el número de plazas son menor o igual que 0.
     */
    public Parking(int numeroPlazas, int numeroCoches) throws IllegalArgumentException{
        if (numeroCoches <= 0){
            throw new IllegalArgumentException("El número de coches tiene que ser mayor que 0");
        }
        setCola(new LinkedList<>());
        setNumeroPlazas(numeroPlazas);
        setPlazasOcupadas(0);

        Plaza[] plazas = new Plaza[numeroPlazas];
        for (int i = 0; i < numeroPlazas; i++) {
            plazas[i] = new Plaza(i + 1);
        }
        setPlazas(plazas);

        Coche[] coches = new Coche[numeroCoches];
        for (int i = 0; i < numeroCoches; i++) {
            coches[i] = new Coche(i + 1, this);
        }
        setCoches(coches);
    }

    /**
     * Método para obtener la cola de espera del parking.
     *
     * @return Cola de espera del parking.
     */
    public Queue<Coche> getCola() {
        return cola;
    }

    /**
     * Método para establecer la cola de espera del parking.
     *
     * @param cola Cola de espera del parking.
     */
    public void setCola(Queue<Coche> cola) {
        this.cola = cola;
    }

    /**
     * Método para obtener el número de plazas del parking.
     * @return Número de plazas del parking.
     */
    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    /**
     * Método para establecer el array de objetos Plaza del parking.
     * @param numeroPlazas Dimensión del array.
     * @throws IllegalArgumentException Si el número de plazas es menor o igual que cero.
     */
    public void setNumeroPlazas(int numeroPlazas) throws IllegalArgumentException{
        if (numeroPlazas <= 0){
            throw new IllegalArgumentException("Número de plazas inválido");
        }
        this.numeroPlazas = numeroPlazas;
    }

    /**
     * Método para obtener el número de plazas ocupadas del parking.
     *
     * @return Número de plazas ocupadas del parking.
     */
    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    /**
     * Método para establecer el número de plazas ocupadas del parking.
     *
     * @param plazasOcupadas Número de plazas ocupadas.
     * @throws IllegalArgumentException Si las plazas ocupadas son menores que 0.
     */
    public void setPlazasOcupadas(int plazasOcupadas) throws IllegalArgumentException {
        if (plazasOcupadas < 0) {
            throw new IllegalArgumentException("Las plazas ocupadas no pueden ser negativas");
        }
        this.plazasOcupadas = plazasOcupadas;
    }

    /**
     * Método para obtener el array de plazas del parking.
     *
     * @return Array de objetos Plaza que representan las plazas del parking.
     */
    public Plaza[] getPlazas() {
        return plazas;
    }

    /**
     * Método para establecer el array de plazas del parking.
     *
     * @param plazas Array de objetos Plaza que representan las plazas del parking.
     */
    public void setPlazas(Plaza[] plazas) {
        this.plazas = plazas;
    }

    /**
     * Método para obtener el array de coches en el parking.
     *
     * @return Arreglo de objetos Coche que representan los coches en el parking.
     */
    public Coche[] getCoches() {
        return coches;
    }

    /**
     * Método para establecer el array de coches en el parking.
     *
     * @param coches Array de objetos Coche que representan los coches en el parking.
     */
    public void setCoches(Coche[] coches) {
        this.coches = coches;
    }


    /**
     * Método que gestiona la entrada de un coche al parking. Si todas las plazas están ocupadas,
     * el coche se coloca en la cola de espera.
     *
     * @param coche Coche que desea entrar al parking.
     */
    public synchronized void entradaAlParking(Coche coche) {
        System.out.printf("El coche %d ha llegado al parking%n", coche.getIdCoche());
        if (this.plazasOcupadas == numeroPlazas){
            ponerseEnLaCola(coche);
        }

        for (Plaza plaza : getPlazas()){
            if (!plaza.isOcupada()){
                plaza.setOcupada(true);
                plazasOcupadas++;
                coche.setPlazaAsignada(plaza);
                System.out.printf("El coche %d ha ocupado la plaza %d%n", coche.getIdCoche(), plaza.getIdPlaza());
                break;
            }
        }    }

    /**
     * Método que gestiona la salida de un coche del parking. Libera la plaza ocupada por el coche
     * y permite que el siguiente coche en la cola de espera acceda al parking.
     *
     * @param coche Coche que desea salir del parking.
     */
    public synchronized void salidaDelParking(Coche coche) {
        Plaza plaza = coche.getPlazaAsignada();
        coche.setPermisoPaso(false);
        System.out.printf("El coche %d se marcha del parking y deja libre la plaza %d%n", coche.getIdCoche(), plaza.getIdPlaza());
        plaza.setOcupada(false);
        plazasOcupadas--;

        if (!cola.isEmpty()){
            Coche primerCocheEnCola = cola.poll();
            primerCocheEnCola.setPermisoPaso(true);
        }

        notifyAll();
    }

    /**
     * Método privado que agrega un coche a la cola de espera del parking y espera hasta que
     * se le otorga permiso de paso.
     *
     * @param coche Coche que se agrega a la cola de espera.
     */
    private void ponerseEnLaCola(Coche coche) {
        this.cola.add(coche);
        System.out.printf("El coche %d se ha puesto en la cola%n", coche.getIdCoche());
        while (!coche.tienePermisoDePaso()){
            try {
                wait();
            } catch (InterruptedException e) {
                // Como el coche va a estar atrapado en este bucle hasta que el parking le active los permisos podemos
                // dejar esta excepción sin tratar. Si wait falla pero el parking todavía no le ha dado los permisos va
                // a repetir el bucle e intentará invocar a wait otra vez, así que se puede recuperar del error
            }
        }
    }

    public static void main(String[] args) {
        try {
            Parking parking = new Parking(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            for (Coche coche : parking.getCoches()){
                coche.start();
            }
        }catch (NumberFormatException e){
            System.out.println("FALLO DE EJECUCIÓN POR ARGUMENTOS INVÁLIDOS");
            System.out.println("El programa necesita dos números enteros. El primero representa las plazas del parking y el segundo el número de coches que quieren acceder.");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }}

