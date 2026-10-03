//irvin

package Modelo;

import Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CitaMedica {

    private int codigoCita;
    private int codigoPaciente;
    private int codigoDoctor;
    private String fechaCita;
    private String horaCita;
    private String motivoConsulta;
    private String estadoCita;

    public CitaMedica(int codigoPaciente, int codigoDoctor,
                      String fechaCita, String horaCita,
                      String motivoConsulta, String estadoCita) {
        this.codigoPaciente = codigoPaciente;
        this.codigoDoctor = codigoDoctor;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivoConsulta = motivoConsulta;
        this.estadoCita = estadoCita;
    }

    public CitaMedica(int codigoCita, int codigoPaciente, int codigoDoctor,
                      String fechaCita, String horaCita,
                      String motivoConsulta, String estadoCita) {
        this.codigoCita = codigoCita;
        this.codigoPaciente = codigoPaciente;
        this.codigoDoctor = codigoDoctor;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivoConsulta = motivoConsulta;
        this.estadoCita = estadoCita;
    }

    public int getCodigoCita() { return codigoCita; }
    public void setCodigoCita(int codigoCita) { this.codigoCita = codigoCita; }

    public int getCodigoPaciente() { return codigoPaciente; }
    public void setCodigoPaciente(int codigoPaciente) { this.codigoPaciente = codigoPaciente; }

    public int getCodigoDoctor() { return codigoDoctor; }
    public void setCodigoDoctor(int codigoDoctor) { this.codigoDoctor = codigoDoctor; }

    public String getFechaCita() { return fechaCita; }
    public void setFechaCita(String fechaCita) { this.fechaCita = fechaCita; }

    public String getHoraCita() { return horaCita; }
    public void setHoraCita(String horaCita) { this.horaCita = horaCita; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getEstadoCita() { return estadoCita; }
    public void setEstadoCita(String estadoCita) { this.estadoCita = estadoCita; }

    @Override
    public String toString() {
        return "Cita #" + codigoCita
                + " | Pac: " + codigoPaciente
                + " | Doc: " + codigoDoctor
                + " | " + fechaCita + " " + horaCita
                + " | " + estadoCita;
    }


    public static class CitaJDBC {

        private Connection conn;

        public CitaJDBC() {
            conn = ConexionDB.conectar();
        }

        public void insertarCita(CitaMedica cita) throws SQLException {
            if (esFechaPasada(cita.getFechaCita())) {
                throw new SQLException("No se puede agendar en una fecha pasada.");
            }
            if (!doctorDisponible(cita.getCodigoDoctor(), cita.getFechaCita(), cita.getHoraCita())) {
                throw new SQLException("El doctor ya tiene una cita a esa hora.");
            }
            if (!pacienteDisponible(cita.getCodigoPaciente(), cita.getFechaCita(), cita.getHoraCita())) {
                throw new SQLException("El paciente ya tiene una cita a esa hora.");
            }

            String sql = "INSERT INTO citas_medicas "
                    + "(codigo_paciente, codigo_doctor, fecha_cita, hora_cita, motivo_consulta, estado_cita) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cita.getCodigoPaciente());
            ps.setInt(2, cita.getCodigoDoctor());
            ps.setString(3, cita.getFechaCita());
            ps.setString(4, cita.getHoraCita());
            ps.setString(5, cita.getMotivoConsulta());
            ps.setString(6, cita.getEstadoCita());
            ps.executeUpdate();
            ps.close();
        }

        public List<CitaMedica> listarCitas() throws SQLException {
            List<CitaMedica> lista = new ArrayList<>();
            String sql = "SELECT * FROM citas_medicas";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(mapear(rs));
            }
            rs.close();
            st.close();
            return lista;
        }

        public CitaMedica buscarCita(int codigoCita) throws SQLException {
            String sql = "SELECT * FROM citas_medicas WHERE codigo_cita = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoCita);
            ResultSet rs = ps.executeQuery();

            CitaMedica c = null;
            if (rs.next()) {
                c = mapear(rs);
            }
            rs.close();
            ps.close();
            return c;
        }

        public void actualizarCita(CitaMedica cita) throws SQLException {
            CitaMedica original = buscarCita(cita.getCodigoCita());
            if (original == null) {
                throw new SQLException("La cita no existe.");
            }
            if (!original.getEstadoCita().equalsIgnoreCase("Programada")) {
                throw new SQLException("Solo se pueden modificar citas 'Programada'.");
            }

            String sql = "UPDATE citas_medicas SET "
                    + "codigo_paciente=?, codigo_doctor=?, fecha_cita=?, "
                    + "hora_cita=?, motivo_consulta=?, estado_cita=? "
                    + "WHERE codigo_cita=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cita.getCodigoPaciente());
            ps.setInt(2, cita.getCodigoDoctor());
            ps.setString(3, cita.getFechaCita());
            ps.setString(4, cita.getHoraCita());
            ps.setString(5, cita.getMotivoConsulta());
            ps.setString(6, cita.getEstadoCita());
            ps.setInt(7, cita.getCodigoCita());
            ps.executeUpdate();
            ps.close();
        }

        public void cambiarEstado(int codigoCita, String nuevoEstado) throws SQLException {
            String sql = "UPDATE citas_medicas SET estado_cita = ? WHERE codigo_cita = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, codigoCita);
            ps.executeUpdate();
            ps.close();
        }

        public void eliminarCita(int codigoCita) throws SQLException {
            String sql = "DELETE FROM citas_medicas WHERE codigo_cita = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoCita);
            ps.executeUpdate();
            ps.close();
        }

        private CitaMedica mapear(ResultSet rs) throws SQLException {
            return new CitaMedica(
                    rs.getInt("codigo_cita"),
                    rs.getInt("codigo_paciente"),
                    rs.getInt("codigo_doctor"),
                    rs.getString("fecha_cita"),
                    rs.getString("hora_cita"),
                    rs.getString("motivo_consulta"),
                    rs.getString("estado_cita")
            );
        }

        private boolean doctorDisponible(int idDoc, String fecha, String hora) throws SQLException {
            String sql = "SELECT COUNT(*) FROM citas_medicas "
                    + "WHERE codigo_doctor=? AND fecha_cita=? AND hora_cita=? "
                    + "AND estado_cita != 'Cancelada'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idDoc);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            ps.close();
            return count == 0;
        }

        private boolean pacienteDisponible(int idPac, String fecha, String hora) throws SQLException {
            String sql = "SELECT COUNT(*) FROM citas_medicas "
                    + "WHERE codigo_paciente=? AND fecha_cita=? AND hora_cita=? "
                    + "AND estado_cita != 'Cancelada'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPac);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            ps.close();
            return count == 0;
        }

        private boolean esFechaPasada(String fecha) {
            try {
                return java.time.LocalDate.parse(fecha).isBefore(java.time.LocalDate.now());
            } catch (Exception e) {
                return false;
            }
        }
    }
}
