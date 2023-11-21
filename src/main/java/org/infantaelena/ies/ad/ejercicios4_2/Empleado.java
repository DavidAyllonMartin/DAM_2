package org.infantaelena.ies.ad.ejercicios4_2;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
    private String nombre_departamento;
    private Empleado director;

    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fecha_contratacion, String id_trabajo, float salario, float comision, String departamento, Empleado director) {
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
        this.director = director;
    }

    public static void main(String[] args) {
        String urlDB = "jdbc:mysql://192.168.56.101:3306/empleados";
        String user = "admin00";
        String password = "alumno";
        List<Empleado> empleados = buscarEmpleadoPorDepartamento(urlDB, user, password, "administracion");

        empleados.forEach(System.out::println);
    }

    private static List<Empleado> buscarEmpleadoPorDepartamento(String urlDB, String user, String password, String departamento) {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            String selectDirectoresYEmpleados = "SELECT E.id_empleado, E.nombre, E.apellido, E.email, E.telefono, E.fecha_contratacion, E.id_trabajo, E.salario, E.comision, D.nombre_departamento, E.id_director FROM empleados E, departamentos D WHERE E.id_departamento = D.id_departamento AND (D.nombre_departamento = ? OR E.id_empleado IN (SELECT E.id_director FROM empleados E, departamentos D WHERE E.id_departamento = D.id_departamento AND D.nombre_departamento = ?));";
            List<Empleado> directoresYEmpleados = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(selectDirectoresYEmpleados)) {
                //Con este select sacamos todos los empleados del departamento en cuestión y también los directores de estos empleados
                //Esto lo hago porque en la referencia de director de cada empleado se guarda un objeto Empleado y así ya están creados
                //Los busco a todos a la vez por no hacer dos select distintos para guardar empleados por un lado y jefes por otro
                ps.setString(1, departamento);
                ps.setString(2, departamento);

                try (ResultSet rs = ps.executeQuery()) {
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
                        int id_director = rs.getInt("id_director");
                        //Aquí hacemos un truquito. Creamos un objeto Empleado para el director y le metemos solo su ID
                        Empleado director = new Empleado();
                        director.setId_empleado(id_director);
                        Empleado empleado = new Empleado(id_empleado, nombre, apellido, email, telefono, fecha_contratacion, id_trabajo, salario, comision, nombre_departamento, director);
                        //Guardo todos los empleados que he creado, entre los que se encuentran todos los directores, en el arrayList correspondiente
                        directoresYEmpleados.add(empleado);
                    }
                }
            }
            for (Empleado empleado : directoresYEmpleados) {
                int id_director = empleado.getDirector().getId_empleado();
                for (Empleado director : directoresYEmpleados) {
                    if (id_director == director.getId_empleado()) {
                        empleado.setDirector(director);
                        empleados.add(empleado);
                        break;
                    }
                    empleado.setDirector(null);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return empleados;
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

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public Empleado getDirector() {
        return director;
    }

    public void setDirector(Empleado director) {
        this.director = director;
    }

    public static boolean insertarEmpleado(String urlDB, String user, String password, Empleado empleado) {
        boolean insertado = false;
        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            String insert = "INSERT INTO empleados (id_empleado, nombre, apellido, email, telefono, fecha_contratacion, id_trabajo, salario, comision, nombre_departamento, id_director) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement ps = connection.prepareStatement(insert)) {
                ps.setInt(1, empleado.getId_empleado());
                ps.setString(2, empleado.getNombre());
                ps.setString(3, empleado.getApellido());
                ps.setString(4, empleado.getEmail());
                ps.setString(5, empleado.getTelefono());
                ps.setString(6, empleado.getFecha_contratacion());
                ps.setString(7, empleado.getId_trabajo());
                ps.setFloat(8, empleado.getSalario());
                ps.setFloat(9, empleado.getComision());
                ps.setString(10, empleado.getNombre_departamento());
                ps.setInt(11, empleado.getDirector().getId_empleado());

                if (ps.executeUpdate() == 1){
                    insertado = true;
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return insertado;
    }
    public static boolean borrarEmpleado(String urlDB, String user, String password, int id_empleado){
        boolean borrado = false;
        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            String delete = "DELETE FROM empleados WHERE id_empleado = ?";

            try (PreparedStatement ps = connection.prepareStatement(delete)) {
                ps.setInt(1, id_empleado);

                if (ps.executeUpdate() == 1){
                    borrado = true;
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return borrado;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaContratacion = dateFormat.format(this.fecha_contratacion.getTime());

        if (this.director == null) {
            return String.format("id_empleado = %d, nombre = %s, apellido = %s, email = %s, telefono = %s, fecha_contratacion = %s, id_trabajo = %s, salario = %.2f, comision = %.2f, nombre_departamento = %s",
                    id_empleado, nombre, apellido, email, telefono, fechaContratacion, id_trabajo, salario, comision, nombre_departamento);
        } else {
            return String.format("id_empleado = %d, nombre = %s, apellido = %s, email = %s, telefono = %s, fecha_contratacion = %s, id_trabajo = %s, salario = %.2f, comision = %.2f, nombre_departamento = %s" + "\n\t DIRECTOR: %s",
                    id_empleado, nombre, apellido, email, telefono, fechaContratacion, id_trabajo, salario, comision, nombre_departamento, this.director);
        }
    }
}

