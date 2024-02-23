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
    private String rango;

    // Constructor vacío
    public Custodio() {
    }

    // Constructor con parámetros
    public Custodio(int id, String nombres, String cedula, String celular, String direccion, String correo, String subzona, String distrito, String rango) {
        this.id = id;
        this.nombres = nombres;
        this.cedula = cedula;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
        this.subzona = subzona;
        this.distrito = distrito;
        this.rango = rango;
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

    public String getNombre() {
        // Retorna la propiedad que contiene el nombre del custodio.
        return this.nombres; // Asegúrate de que 'nombre' sea el nombre de la propiedad que contiene el nombre del custodio.
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
    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
}
