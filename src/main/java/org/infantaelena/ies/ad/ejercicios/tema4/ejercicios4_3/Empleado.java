package org.infantaelena.ies.ad.ejercicios.tema4.ejercicios4_3;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Empleado {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Calendar fecha_contratacion;
    private String id_trabajo;
    private float salario;
    private float comision;
    private int id_departamento;
    private Empleado director;

    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fecha_contratacion, String id_trabajo, float salario, float comision, int id_departamento, Empleado director) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fecha_contratacion = fecha_contratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
        this.id_departamento = id_departamento;
        this.director = director;
    }

    public static void main(String[] args) {
        String urlDB = "jdbc:mysql://192.168.56.101:3306/empleados";
        String user = "admin00";
        String password = "alumno";

    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_contratacion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.fecha_contratacion.getTime());
    }

    public void setFecha_contratacion(Calendar fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(String id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public int getNombre_idDepartamento() {
        return id_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.id_departamento = id_departamento;
    }

    public Empleado getDirector() {
        return director;
    }

    public void setDirector(Empleado director) {
        this.director = director;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaContratacion = dateFormat.format(this.fecha_contratacion.getTime());

            return String.format("id_empleado = %d, nombre = %s, apellido = %s, email = %s, telefono = %s, fecha_contratacion = %s, id_trabajo = %s, salario = %.2f, comision = %.2f, id_departamento = %d",
                    id_empleado, nombre, apellido, email, telefono, fechaContratacion, id_trabajo, salario, comision, id_departamento);

    }
}

