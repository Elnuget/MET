package modelos;

public class Custodio {
    private int id;
    private String nombres;
    private String cedula;
    private String celular;
    private String direccion;
    private String correo;
    private String subzona;
    private String distrito;

    // Constructor vacío
    public Custodio() {
    }

    // Constructor con parámetros
    public Custodio(int id, String nombres, String cedula, String celular, String direccion, String correo, String subzona, String distrito) {
        this.id = id;
        this.nombres = nombres;
        this.cedula = cedula;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
        this.subzona = subzona;
        this.distrito = distrito;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}