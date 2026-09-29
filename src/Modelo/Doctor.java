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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luise
 */
public class Doctor {
        // Atributos
    private String codigoDoctor;
    private String nombreCompleto;
    private String especialidad;
    private String telefono;
    private String correo;
    private String estado;

    // Constructor
    public Doctor(String codigoDoctor, String nombreCompleto, String especialidad,
                  String telefono, String correo, String estado) {
        this.codigoDoctor = codigoDoctor;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    // Getters y Setters
	public String getCodigoDoctor() { return codigoDoctor; }
    public void setCodigoDoctor(String codigoDoctor) { this.codigoDoctor = codigoDoctor; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
	//Muestra información
    @Override
    public String toString() {
        return "Código Doctor: " + codigoDoctor +
               "\nNombre: " + nombreCompleto +
               "\nEspecialidad: " + especialidad +
               "\nTeléfono: " + telefono +
               "\nCorreo: " + correo +
               "\nEstado: " + estado;
    }
}

// Clase DoctorJDBC (no publica)
class DoctorJDBC {
    private Connection conn;

    public DoctorJDBC(Connection conn) {
        this.conn = conn;
    }
// Insertar nuevo doctor
    public void insertarDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctores (codigoDoctor, nombreCompleto, especialidad, telefono, correo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, doctor.getCodigoDoctor());
        ps.setString(2, doctor.getNombreCompleto());
        ps.setString(3, doctor.getEspecialidad());
        ps.setString(4, doctor.getTelefono());
        ps.setString(5, doctor.getCorreo());
        ps.setString(6, doctor.getEstado());
        ps.executeUpdate();
    }
// Consultar todos los doctores
    public List<Doctor> listarDoctores() throws SQLException {
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM doctores";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Doctor d = new Doctor(
                rs.getString("codigoDoctor"),
                rs.getString("nombreCompleto"),
                rs.getString("especialidad"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("estado")
            );
            lista.add(d);
        }
        return lista;
    }
// Actualizar estado del doctor
    public void actualizarEstado(String codigoDoctor, String nuevoEstado) throws SQLException {
        String sql = "UPDATE doctores SET estado = ? WHERE codigoDoctor = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nuevoEstado);
        ps.setString(2, codigoDoctor);
        ps.executeUpdate();
    }
// Eliminar doctor
    public void eliminarDoctor(String codigoDoctor) throws SQLException {
        String sql = "DELETE FROM doctores WHERE codigoDoctor = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigoDoctor);
        ps.executeUpdate();
    }
    
}
