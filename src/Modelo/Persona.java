package Modelo;

public class Persona {

    private String nombreCompleto;
    private String telefono;
    private String correo;

    public Persona(String nombreCompleto, String telefono, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
