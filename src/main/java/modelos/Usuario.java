package modelos;

public class Usuario {
    private int pk_id_usuario;
    private String usuario;
    private String contraseña;
    private String rol;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los atributos
    public Usuario(int pk_id_usuario, String usuario, String contraseña, String rol) {
        this.pk_id_usuario = pk_id_usuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Getters y Setters
    public int getPk_id_usuario() {
        return pk_id_usuario;
    }

    public void setPk_id_usuario(int pk_id_usuario) {
        this.pk_id_usuario = pk_id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    


    // Método toString para la representación de texto del objeto
    @Override
    public String toString() {
        return "Usuario{" +
                "pk_id_usuario=" + pk_id_usuario +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
