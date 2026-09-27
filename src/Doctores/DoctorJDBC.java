package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private Connection conn;

    public DoctorDAO(Connection conn) {
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
