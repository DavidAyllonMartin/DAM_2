package org.infantaelena.ies.psp.UD2.practicas.practica2_5;

public class SacarDinero extends Thread {
    String nombre;
    private final Cuenta cuenta;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SacarDinero(String nombre, Cuenta cuenta) {
        setNombre(nombre);
        this.cuenta = cuenta;
    }

    public void run() {

        cuenta.consultarSaldo(getNombre());

        for (int i = 0; i < 3; i++) {
            cuenta.agregarSaldo(10, getNombre());
        }

        for (int x = 1; x <= 4; x++) {
            cuenta.RetirarDinero(10, getNombre());
        }

        cuenta.consultarSaldo(getNombre());
    }

}
