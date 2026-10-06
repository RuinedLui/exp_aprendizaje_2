/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {

    // Atributos propios de Paciente
    private int codigoPaciente;
    private int edad;
    private String tipoPaciente;
    private String estado;

    // Constructor
    public Paciente(
            int codigoPaciente,
            String nombreCompleto,
            int edad,
            String telefono,
            String correo,
            String tipoPaciente,
            String estado
    ) {

        // Atributos heredados de Persona
        super(nombreCompleto, telefono, correo);

        // Atributos propios de Paciente
        this.codigoPaciente = codigoPaciente;
        this.edad = edad;
        this.tipoPaciente = tipoPaciente;
        this.estado = estado;
    }

    // Getters y Setters

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Mostrar información
    @Override
    public String toString() {
        return "Código Paciente: " + codigoPaciente
                + "\nNombre: " + getNombreCompleto()
                + "\nEdad: " + edad
                + "\nTeléfono: " + getTelefono()
                + "\nCorreo: " + getCorreo()
                + "\nTipo de paciente: " + tipoPaciente
                + "\nEstado: " + estado;
    }

    // =====================================================
    // Operaciones JDBC de Paciente
    // =====================================================

    public static class PacienteJDBC {

        private Connection conn;

        // Constructor
        public PacienteJDBC() {
            conn = ConexionDB.conectar();
        }

        // Insertar nuevo paciente
        public void insertarPaciente(Paciente paciente) throws SQLException {

            String sql = "INSERT INTO pacientes "
                    + "(nombre_completo, edad, telefono, "
                    + "correo_electronico, tipo_paciente, estado_paciente) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, paciente.getNombreCompleto());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getTelefono());
            ps.setString(4, paciente.getCorreo());
            ps.setString(5, paciente.getTipoPaciente());
            ps.setString(6, paciente.getEstado());

            ps.executeUpdate();

            ps.close();
        }

        // Consultar todos los pacientes
        public List<Paciente> listarPacientes() throws SQLException {

            List<Paciente> lista = new ArrayList<>();

            String sql = "SELECT * FROM pacientes";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Paciente paciente = new Paciente(
                        rs.getInt("codigo_paciente"),
                        rs.getString("nombre_completo"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getString("tipo_paciente"),
                        rs.getString("estado_paciente")
                );

                lista.add(paciente);
            }

            rs.close();
            st.close();

            return lista;
        }

        // Actualizar estado del paciente
        public void actualizarEstado(
                int codigoPaciente,
                String nuevoEstado
        ) throws SQLException {

            String sql = "UPDATE pacientes "
                    + "SET estado_paciente = ? "
                    + "WHERE codigo_paciente = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nuevoEstado);
            ps.setInt(2, codigoPaciente);

            ps.executeUpdate();

            ps.close();
        }

        // Eliminar paciente
        public void eliminarPaciente(int codigoPaciente)
                throws SQLException {

            String sql = "DELETE FROM pacientes "
                    + "WHERE codigo_paciente = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, codigoPaciente);

            ps.executeUpdate();

            ps.close();
        }
    }
}