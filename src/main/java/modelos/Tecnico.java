package modelos;

public class Tecnico {
    private int id;
    private String nombres;
    private String cedula;
    private String celular;

    // Constructor vacío
    public Tecnico() {
    }

    // Constructor con parámetros
    public Tecnico(int id, String nombres, String cedula, String celular) {
        this.id = id;
        this.nombres = nombres;
        this.cedula = cedula;
        this.celular = celular;
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

    // Método toString para la representación en cadena del objeto (opcional)
    @Override
    public String toString() {
        return "Tecnico{" +
               "id=" + id +
               ", nombres='" + nombres + '\'' +
               ", cedula='" + cedula + '\'' +
               ", celular='" + celular + '\'' +
               '}';
    }
}
