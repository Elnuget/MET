package dao;

import java.sql.*;
import modelos.Entrega;

public class EntregaDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private Connection jdbcConnection;

    public EntregaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertEntrega(Entrega entrega) throws SQLException {
        String sql = "INSERT INTO tbl_entrega (fecha_entrega, observaciones, fk_id_mantenimiento) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(entrega.getFecha_entrega().getTime()));
        statement.setString(2, entrega.getObservaciones());
        statement.setInt(3, entrega.getFk_id_mantenimiento());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean existeEntregaPorMantenimiento(int fk_id_mantenimiento) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM tbl_entrega WHERE fk_id_mantenimiento = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, fk_id_mantenimiento);

        ResultSet resultSet = statement.executeQuery();
        boolean existe = false;

        if (resultSet.next()) {
            existe = resultSet.getInt("total") > 0;
        }

        resultSet.close();
        statement.close();
        disconnect();

        return existe;
    }
    public Entrega obtenerEntregaPorMantenimiento(int fk_id_mantenimiento) throws SQLException {
        String sql = "SELECT * FROM tbl_entrega WHERE fk_id_mantenimiento = ?";
        Entrega entrega = null;

        try {
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, fk_id_mantenimiento);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int pk_id_entrega = resultSet.getInt("pk_id_entrega");
                Date fecha_entrega = resultSet.getDate("fecha_entrega");
                String observaciones = resultSet.getString("observaciones");
                // Asumiendo que fk_id_mantenimiento ya lo tienes, no es necesario obtenerlo de nuevo del ResultSet

                entrega = new Entrega(pk_id_entrega, fecha_entrega, observaciones, fk_id_mantenimiento);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            disconnect();
        }

        return entrega;
    }

}
