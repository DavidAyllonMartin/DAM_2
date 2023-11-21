package org.infantaelena.ies.ad.ejercicios4_3;

import org.infantaelena.ies.ad.ejercicios4_2.Empleado;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        String urlDB = "jdbc:mysql://192.168.56.101:3306/empleados";
        String user = "admin00";
        String password = "alumno";

        buscarEmpleadosOrdenadosPorDepartamento(urlDB, user, password, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    public static void buscarEmpleadosOrdenadosPorDepartamento(String urlDB, String user, String password, int resultSetType, int resultSetConcurrency){
        try (Connection connection = DriverManager.getConnection(urlDB, user, password)){
            String select = "SELECT * FROM empleados ORDER BY id_departamento;";
            try (Statement st = connection.createStatement(resultSetType, resultSetConcurrency)){
                try (ResultSet rs = st.executeQuery(select)){
                    int filaInicio = 0;
                    while (rs.next()){
                        if (rs.getInt("id_departamento") == 30){
                            filaInicio = rs.getRow();
                            break;
                        }
                    }
                    rs.afterLast();
                    while (rs.previous()){
                        if (rs.getInt("id_departamento") == 30){
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
                            System.out.println(empleado);
                        }
                        if (rs.getRow() == filaInicio){
                            break;
                        }
                }
            }catch (SQLException e){}
            }catch (SQLException e){}
        }catch (SQLException e){

        }
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

}
