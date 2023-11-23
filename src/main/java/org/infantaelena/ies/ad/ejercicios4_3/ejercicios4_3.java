package org.infantaelena.ies.ad.ejercicios4_3;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ejercicios4_3 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.101:3306/empleados", "admin00", "alumno")) {
            //mostrarEmpleadosDepartamento(connection, 30);
            //actualizarSalarios(connection);
            borrarLocalizacionesSinDepartamentos(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarEmpleadosDepartamento(Connection connection, int idDepartamento) throws SQLException {
        try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = "SELECT * FROM empleados ORDER BY id_departamento";
            try (ResultSet rs = st.executeQuery(query)) {
                int inicioDepartamento = -1;
                int finalDepartamento = -1;

                while (rs.next()) {
                    int rsIdDepartamento = rs.getInt("id_departamento");

                    if (rsIdDepartamento == idDepartamento) {
                        finalDepartamento = rs.getRow();
                        if (inicioDepartamento == -1) {
                            inicioDepartamento = finalDepartamento;
                        }
                    }
                }

                if (inicioDepartamento != -1 && finalDepartamento != -1) {
                    System.out.println("Empleados del departamento " + idDepartamento + " en orden inverso:");
                    rs.absolute(finalDepartamento);
                    while (rs.getRow() >= inicioDepartamento) {
                        System.out.println("ID: " + rs.getInt("id_empleado") + ", Nombre: " + rs.getString("nombre"));
                        rs.previous();
                    }
                } else {
                    System.out.println("No se encontraron empleados en el departamento " + idDepartamento + ".");
                }
            }
        }
    }

    private static void actualizarSalarios(Connection connection) throws SQLException {

        Map<String, Integer> salariosMaximos = new HashMap<>();
        Map<String, Integer> salariosMinimos = new HashMap<>();

        try (Statement st = connection.createStatement()){
            String query = "SELECT id_trabajo, min_salario, max_salario FROM trabajos";
            try (ResultSet rs = st.executeQuery(query)){
                while (rs.next()){
                    salariosMinimos.put(rs.getString("id_trabajo"), rs.getInt("min_salario"));
                    salariosMaximos.put(rs.getString("id_trabajo"), rs.getInt("max_salario"));
                }
            }
        }

        try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            //Cagadita al no tener en cuenta lo que ponía en los apuntes de que solo se puede actualizar un ResultSet que haga referencia a más de una tabla.
            //Le he hecho un pequeño apaño porque me gustaba mi solución, pero lo suyo sería hacer una consulta para sacar los máximos y mínimos y tirar de ella
            String query = "SELECT * FROM empleados";

            try (ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    int idEmpleado = rs.getInt("id_empleado");
                    String idTrabajo = rs.getString("id_trabajo");
                    double salarioActual = rs.getDouble("salario");
                    double nuevoSalario = 0;
                    if (idEmpleado % 2 == 0){
                        int maxSalario = salariosMaximos.get(idTrabajo);
                        //double nuevoSalario = (salarioActual * 2 > maxSalario) ? maxSalario : salarioActual * 2;
                        //Mi planteamiento inicial era con la expresión ternaria de arriba, pero Intellij Idea me sugiere la solución de abajo, que me ha gustado más.
                        nuevoSalario = Math.min(salarioActual * 2, maxSalario);
                    }else {
                        int minSalario = salariosMaximos.get(idTrabajo);
                        nuevoSalario = Math.max(salarioActual / 2, minSalario);
                    }

                    rs.updateDouble("salario", nuevoSalario);
                    rs.updateRow();
                }
            }
        }
    }

    private static void borrarLocalizacionesSinDepartamentos(Connection connection) throws SQLException {
        try (Statement localizacionesStatement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet localizacionesResultSet = localizacionesStatement.executeQuery("SELECT * FROM localizaciones");
             Statement departamentosStatement = connection.createStatement();
             ResultSet departamentosResultSet = departamentosStatement.executeQuery("SELECT DISTINCT id_localizacion FROM departamentos")) {

            Set<Integer> localizacionesConDepartamentos = new HashSet<>();

            while (departamentosResultSet.next()) {
                int idLocalizacion = departamentosResultSet.getInt("id_localizacion");
                localizacionesConDepartamentos.add(idLocalizacion);
            }

            while (localizacionesResultSet.next()) {
                int idLocalizacion = localizacionesResultSet.getInt("id_localizacion");

                if (!localizacionesConDepartamentos.contains(idLocalizacion)) {
                    localizacionesResultSet.deleteRow();
                }
            }
        }
    }

}
