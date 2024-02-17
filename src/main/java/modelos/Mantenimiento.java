package modelos;

import java.util.Date;

public class Mantenimiento {
    private int pk_id_mantenimiento;
    private String tipo;
    private String descripcion;
    private Date fecha_recepcion;
    private Date fecha_entrega;
    private String observacion;
    private Integer fk_id_radio; // Integer para permitir null en caso de no asignarse
    private Integer fk_id_tecnico; // Integer para permitir null en caso de no asignarse

    // Constructor vacío
    public Mantenimiento() {
    }

    // Constructor con todos los atributos
    public Mantenimiento(int pk_id_mantenimiento, String tipo, String descripcion, Date fecha_recepcion, String observacion, Integer fk_id_radio, Integer fk_id_tecnico) {
        this.pk_id_mantenimiento = pk_id_mantenimiento;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha_recepcion = fecha_recepcion;
       
        this.observacion = observacion;
        this.fk_id_radio = fk_id_radio;
        this.fk_id_tecnico = fk_id_tecnico;
    }

    // Getters y Setters
    public int getPk_id_mantenimiento() {
        return pk_id_mantenimiento;
    }

    public void setPk_id_mantenimiento(int pk_id_mantenimiento) {
        this.pk_id_mantenimiento = pk_id_mantenimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

   

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getFk_id_radio() {
        return fk_id_radio;
    }

    public void setFk_id_radio(Integer fk_id_radio) {
        this.fk_id_radio = fk_id_radio;
    }

    public Integer getFk_id_tecnico() {
        return fk_id_tecnico;
    }

    public void setFk_id_tecnico(Integer fk_id_tecnico) {
        this.fk_id_tecnico = fk_id_tecnico;
    }

    // Método toString para la representación de texto del objeto
    @Override
    public String toString() {
        return "Mantenimiento{" +
                "pk_id_mantenimiento=" + pk_id_mantenimiento +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_recepcion=" + fecha_recepcion +
                
                ", observacion='" + observacion + '\'' +
                ", fk_id_radio=" + fk_id_radio +
                ", fk_id_tecnico=" + fk_id_tecnico +
                '}';
    }

    
}
