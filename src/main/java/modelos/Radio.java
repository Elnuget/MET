package modelos;

public class Radio {
    private int pk_id_radio;
    private String tipo;
    private String modelo;
    private String marca;
    private String serie;
    private Integer fk_id_custodio; // Utilizo Integer para permitir nulls

    // Constructor vacío
    public Radio() {
    }

    // Constructor con todos los atributos
    public Radio(int pk_id_radio, String tipo, String modelo, String marca, String serie, Integer fk_id_custodio) {
        this.pk_id_radio = pk_id_radio;
        this.tipo = tipo;
        this.modelo = modelo;
        this.marca = marca;
        this.serie = serie;
        this.fk_id_custodio = fk_id_custodio;
    }

    // Getters y Setters
    public int getPk_id_radio() {
        return pk_id_radio;
    }

    public void setPk_id_radio(int pk_id_radio) {
        this.pk_id_radio = pk_id_radio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getFk_id_custodio() {
        return fk_id_custodio;
    }

    public void setFk_id_custodio(Integer fk_id_custodio) {
        this.fk_id_custodio = fk_id_custodio;
    }

    // Método toString para la representación de texto del objeto
    @Override
    public String toString() {
        return "Radio{" +
                "pk_id_radio=" + pk_id_radio +
                ", tipo='" + tipo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", serie='" + serie + '\'' +
                ", fk_id_custodio=" + fk_id_custodio +
                '}';
    }
}
