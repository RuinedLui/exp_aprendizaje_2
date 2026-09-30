package Modelo;

import Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class Doctor extends Persona {

    // Atributos propios de Doctor
    private int codigoDoctor;
    private String especialidad;
    private String estado;

    // Constructor
    public Doctor(
            int codigoDoctor,
            String nombreCompleto,
            String especialidad,
            String telefono,
            String correo,
            String estado
    ) {

        // Atributos heredados de Persona
        super(nombreCompleto, telefono, correo);

        // Atributos propios de Doctor
        this.codigoDoctor = codigoDoctor;
        this.especialidad = especialidad;
=======
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
>>>>>>> 03ed681bafc0a5d1893f37066ddb4c9c38198b98
        this.estado = estado;
    }

    // Getters y Setters
<<<<<<< HEAD
    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(int codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
        return "Código Doctor: " + codigoDoctor
                + "\nNombre: " + getNombreCompleto()
                + "\nEspecialidad: " + especialidad
                + "\nTeléfono: " + getTelefono()
                + "\nCorreo: " + getCorreo()
                + "\nEstado: " + estado;
    }


    // =====================================================
    // Operaciones JDBC de Doctor
    // =====================================================

    public static class DoctorJDBC {

        private Connection conn;

        // Constructor
        public DoctorJDBC() {
            conn = ConexionDB.conectar();
        }

        // Insertar nuevo doctor
        public void insertarDoctor(Doctor doctor) throws SQLException {

            String sql = "INSERT INTO doctores "
                    + "(nombre_completo, especialidad, telefono, "
                    + "correo_electronico, estado_doctor) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, doctor.getNombreCompleto());
            ps.setString(2, doctor.getEspecialidad());
            ps.setString(3, doctor.getTelefono());
            ps.setString(4, doctor.getCorreo());
            ps.setString(5, doctor.getEstado());

            ps.executeUpdate();

            ps.close();
        }

        // Consultar todos los doctores
        public List<Doctor> listarDoctores() throws SQLException {

            List<Doctor> lista = new ArrayList<>();

            String sql = "SELECT * FROM doctores";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Doctor doctor = new Doctor(
                        rs.getInt("codigo_doctor"),
                        rs.getString("nombre_completo"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getString("estado_doctor")
                );

                lista.add(doctor);
            }

            rs.close();
            st.close();

            return lista;
        }

        // Actualizar estado del doctor
        public void actualizarEstado(
                int codigoDoctor,
                String nuevoEstado
        ) throws SQLException {

            String sql = "UPDATE doctores "
                    + "SET estado_doctor = ? "
                    + "WHERE codigo_doctor = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nuevoEstado);
            ps.setInt(2, codigoDoctor);

            ps.executeUpdate();

            ps.close();
        }

        // Eliminar doctor
        public void eliminarDoctor(int codigoDoctor)
                throws SQLException {

            String sql = "DELETE FROM doctores "
                    + "WHERE codigo_doctor = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, codigoDoctor);

            ps.executeUpdate();

            ps.close();
        }
    }
=======
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
    
>>>>>>> 03ed681bafc0a5d1893f37066ddb4c9c38198b98
}
