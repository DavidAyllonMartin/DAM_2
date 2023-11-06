package org.infantaelena.ies.psp.UD2.practicas.practica2_5;

public class Cuenta {

    private int saldo;

    public Cuenta(int saldo) {
        this.saldo = saldo;
    }

    private synchronized int getSaldo(){
        return this.saldo;
    }

    public void consultarSaldo(String nombre) {
        System.out.println("Hola, " + nombre + ": el saldo en la cuenta es " + getSaldo());
    }

    public synchronized void agregarSaldo(int cantidad, String nombre){
        System.out.println(nombre + ": va a agregar saldo (el actual es: " + getSaldo() + " )");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sumar(cantidad);
        System.out.println("\t" + nombre + " agrega => " + cantidad + " Saldo Actual ( " + getSaldo() + " )");
    }

    public synchronized void RetirarDinero(int cantidad, String nombre) {
        if (getSaldo() >= cantidad) {
            System.out.println(nombre + " : va a retirar saldo (el actual es: " + getSaldo() + " )");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            restar(cantidad);
            System.out.println("\t" + nombre + " retira => " + cantidad + " Saldo Actual ( " + getSaldo() + " )");
        } else {
            System.out.println(nombre + " No puede retirar la cantidad " + cantidad + "porque el saldo es: " + getSaldo() + " ");
        }
        if (getSaldo() < 0) {
            System.out.println("SALDO NEGATIVO: " + getSaldo());
        }
    }

    private void restar(int cantidad) {
        this.saldo = this.saldo - cantidad;
    }
    private void sumar(int cantidad){
        this.saldo = this.saldo + cantidad;
    }
}
