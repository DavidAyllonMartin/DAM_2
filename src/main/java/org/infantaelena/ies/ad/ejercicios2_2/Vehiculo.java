package org.infantaelena.ies.ad.ejercicios2_2;

public class Vehiculo {
    private String nombre;
    private int numero;
    private int tiempo;

    public Vehiculo(String nombre, int numero, String tiempo){
        setNombre(nombre);
        setNumero(numero);
        setTiempo(tiempo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTiempo() {
        int segundos = this.tiempo;

        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segundosRestantes = segundos % 60;

        return horas + "H" + minutos + "M" + segundosRestantes + "S";
    }

    public void setTiempo(String tiempo) {
        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        try {
            String[] partes = tiempo.split("[HMS]");

            if (partes.length >= 1) {
                horas = Integer.parseInt(partes[0]);
            }
            if (partes.length >= 2) {
                minutos = Integer.parseInt(partes[1]);
            }
            if (partes.length >= 3) {
                segundos = Integer.parseInt(partes[2]);
            }

            this.tiempo = horas * 3600 + minutos * 60 + segundos;
        }catch (Exception e){
            this.tiempo = 0;
        }
    }
}
