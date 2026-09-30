package clinica;

public class Doctor {
    // Atributos
    private String codigoDoctor;
    private String nombreCompleto;
    private String especialidad;   // Medicina General, Psicología, Nutrición, Fisioterapia
    private String telefono;
    private String correo;
    private String estado;         // Disponible o No disponible

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

    // Método para mostrar información
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
