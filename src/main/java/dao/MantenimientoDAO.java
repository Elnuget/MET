package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Mantenimiento;

public class MantenimientoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private Connection jdbcConnection;

    public MantenimientoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertMantenimiento(Mantenimiento mantenimiento) throws SQLException {
        String sql = "INSERT INTO tbl_mantenimiento (Tipo, Descripción, Fecha_recepción, Fecha_entrega, Observación, fk_id_radio, fk_id_tecnico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, mantenimiento.getTipo());
        statement.setString(2, mantenimiento.getDescripcion());
        statement.setDate(3, new java.sql.Date(mantenimiento.getFecha_recepcion().getTime()));
        statement.setDate(4, new java.sql.Date(mantenimiento.getFecha_entrega().getTime()));
        statement.setString(5, mantenimiento.getObservacion());
        if (mantenimiento.getFk_id_radio() != null) {
            statement.setInt(6, mantenimiento.getFk_id_radio());
        } else {
            statement.setNull(6, Types.INTEGER);
        }
        if (mantenimiento.getFk_id_tecnico() != null) {
            statement.setInt(7, mantenimiento.getFk_id_tecnico());
        } else {
            statement.setNull(7, Types.INTEGER);
        }

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Mantenimiento> selectAllMantenimientos() throws SQLException {
        List<Mantenimiento> mantenimientos = new ArrayList<>();
        String sql = "SELECT * FROM tbl_mantenimiento";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("pk_id_mantenimiento");
            String tipo = resultSet.getString("Tipo");
            String descripcion = resultSet.getString("Descripción");
            Date fecha_recepcion = resultSet.getDate("Fecha_recepción");
            Date fecha_entrega = resultSet.getDate("Fecha_entrega");
            String observacion = resultSet.getString("Observación");
            Integer fk_id_radio = resultSet.getInt("fk_id_radio");
            if (resultSet.wasNull()) fk_id_radio = null;
            Integer fk_id_tecnico = resultSet.getInt("fk_id_tecnico");
            if (resultSet.wasNull()) fk_id_tecnico = null;

            mantenimientos.add(new Mantenimiento(id, tipo, descripcion, fecha_recepcion, fecha_entrega, observacion, fk_id_radio, fk_id_tecnico));
        }

        resultSet.close();
        statement.close();

        disconnect();
        return mantenimientos;
    }

    // Implementa los métodos deleteMantenimiento, updateMantenimiento, y getMantenimiento siguiendo el patrón del UsuarioDAO.
}
