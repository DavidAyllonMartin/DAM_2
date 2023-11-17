package org.infantaelena.ies.ad.ejercicios4_1;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Empleado_4_1_2 {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Calendar fecha_contratacion;
    private String id_trabajo;
    private float salario;
    private float comision;
    private String nombre_departamento;

    public Empleado_4_1_2(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fecha_contratacion, String id_trabajo, float salario, float comision, String departamento) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fecha_contratacion = fecha_contratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
        this.nombre_departamento = departamento;
    }

    public static void main(String[] args) {
        String urlDB = "jdbc:mysql://192.168.56.101:3306/empleados";
        String user = "admin00";
        String password = "alumno";
        List<Empleado_4_1_2> empleados = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            try (Statement st = connection.createStatement()) {

                String selectEmpleados = "SELECT E.id_empleado, E.nombre, E.apellido, E.email, E.telefono, E.fecha_contratacion, E.id_trabajo, E.salario, E.comision, D.nombre_departamento FROM empleados E, departamentos D WHERE E.id_departamento = D.id_departamento AND (D.nombre_departamento = 'Ejecutivo' OR D.nombre_departamento = 'Marketing');";

                try (ResultSet rs = st.executeQuery(selectEmpleados)) {
                    while (rs.next()) {
                        int id_empleado = rs.getInt("id_empleado");
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        String email = rs.getString("email");
                        String telefono = rs.getString("telefono");
                        Date fecha_con = rs.getDate("fecha_contratacion");
                        Calendar fecha_contratacion = new GregorianCalendar();
                        fecha_contratacion.setTime(fecha_con);
                        String id_trabajo = rs.getString("id_trabajo");
                        float salario = rs.getFloat("salario");
                        float comision = rs.getFloat("comision");
                        String nombre_departamento = rs.getString("nombre_departamento");

                        Empleado_4_1_2 empleado = new Empleado_4_1_2(id_empleado, nombre, apellido, email, telefono, fecha_contratacion, id_trabajo, salario, comision, nombre_departamento);
                        empleados.add(empleado);
                    }
                }
            }
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        empleados.forEach(System.out::println);

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

    public Calendar getFecha_contratacion() {
        return fecha_contratacion;
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

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return "id_empleado = " + id_empleado +
                ", nombre = " + nombre +
                ", apellido = " + apellido +
                ", email = " + email +
                ", telefono = " + telefono +
                ", fecha_contratacion = " + dateFormat.format(fecha_contratacion.getTime()) +
                ", id_trabajo = " + id_trabajo +
                ", salario = " + salario +
                ", comision = " + comision +
                ", nombre_departamento = " + nombre_departamento;
    }
}
