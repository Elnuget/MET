package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Usuario;

public class UsuarioDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private Connection jdbcConnection;

    public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tbl_usuario (Usuario, Contraseña, rol) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getContraseña());
        statement.setString(3, usuario.getRol());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Usuario> selectAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM tbl_usuario";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("pk_id_usuario");
            String usuario = resultSet.getString("Usuario");
            String contraseña = resultSet.getString("Contraseña"); // Ten en cuenta la seguridad aquí
            String rol = resultSet.getString("rol");

            usuarios.add(new Usuario(id, usuario, contraseña, rol));
        }

        resultSet.close();
        statement.close();

        disconnect();
        return usuarios;
    }

    public boolean deleteUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM tbl_usuario where pk_id_usuario = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, usuario.getPk_id_usuario());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE tbl_usuario SET Usuario = ?, Contraseña = ?, rol = ? WHERE pk_id_usuario = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getContraseña());
        statement.setString(3, usuario.getRol());
        statement.setInt(4, usuario.getPk_id_usuario());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Usuario getUsuario(int id) throws SQLException {
        Usuario user = null;
        String sql = "SELECT * FROM tbl_usuario WHERE pk_id_usuario = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String usuario = resultSet.getString("Usuario");
            String contraseña = resultSet.getString("Contraseña");
            String rol = resultSet.getString("rol");

            user = new Usuario(id, usuario, contraseña, rol);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return user;
    }
}
