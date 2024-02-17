package modelos;

public class Entrega {
    private int pk_id_entrega;
    private java.util.Date fecha_entrega;
    private String observaciones;
    private int fk_id_mantenimiento;

    // Constructor vacío
    public Entrega() {
    }

    // Constructor con todos los atributos
    public Entrega(int pk_id_entrega, java.util.Date fecha_entrega, String observaciones, int fk_id_mantenimiento) {
        this.pk_id_entrega = pk_id_entrega;
        this.fecha_entrega = fecha_entrega;
        this.observaciones = observaciones;
        this.fk_id_mantenimiento = fk_id_mantenimiento;
    }

    // Getters y Setters
    public int getPk_id_entrega() {
        return pk_id_entrega;
    }

    public void setPk_id_entrega(int pk_id_entrega) {
        this.pk_id_entrega = pk_id_entrega;
    }

    public java.util.Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(java.util.Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getFk_id_mantenimiento() {
        return fk_id_mantenimiento;
    }

    public void setFk_id_mantenimiento(int fk_id_mantenimiento) {
        this.fk_id_mantenimiento = fk_id_mantenimiento;
    }

    // Método toString para la representación de texto del objeto
    @Override
    public String toString() {
        return "Entrega{" +
                "pk_id_entrega=" + pk_id_entrega +
                ", fecha_entrega=" + fecha_entrega +
                ", observaciones='" + observaciones + '\'' +
                ", fk_id_mantenimiento=" + fk_id_mantenimiento +
                '}';
    }
}
