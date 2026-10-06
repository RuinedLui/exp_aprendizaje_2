package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistema_citas_medicas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Luis2108";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion exitosa a MySQL");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

        return conexion;
    }
}
