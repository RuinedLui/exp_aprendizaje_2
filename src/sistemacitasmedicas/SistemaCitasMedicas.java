/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemacitasmedicas;


import Conexion.ConexionDB;
import java.sql.Connection;

/**
 *
 * @author luise
 */
public class SistemaCitasMedicas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            System.out.println("La aplicacion esta conectada a la base de datos");
        } else {
            System.out.println("No se pudo realizar la conexion");
        }
    }
    
}
