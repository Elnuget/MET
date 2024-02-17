package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        String sql = "INSERT INTO tbl_mantenimiento (Tipo, Descripción, Fecha_recepción, Observación, fk_id_radio, fk_id_tecnico) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, mantenimiento.getTipo());
        statement.setString(2, mantenimiento.getDescripcion());
        statement.setDate(3, new java.sql.Date(mantenimiento.getFecha_recepcion().getTime()));
        statement.setString(4, mantenimiento.getObservacion()); // Corrección aquí, usando el índice 4
        if (mantenimiento.getFk_id_radio() != null) {
            statement.setInt(5, mantenimiento.getFk_id_radio()); // Ahora usando el índice 5
        } else {
            statement.setNull(5, Types.INTEGER);
        }
        if (mantenimiento.getFk_id_tecnico() != null) {
            statement.setInt(6, mantenimiento.getFk_id_tecnico()); // Y finalmente el índice 6
        } else {
            statement.setNull(6, Types.INTEGER);
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

            String observacion = resultSet.getString("Observación");
            Integer fk_id_radio = resultSet.getInt("fk_id_radio");
            if (resultSet.wasNull()) {
                fk_id_radio = null;
            }
            Integer fk_id_tecnico = resultSet.getInt("fk_id_tecnico");
            if (resultSet.wasNull()) {
                fk_id_tecnico = null;
            }

            mantenimientos.add(new Mantenimiento(id, tipo, descripcion, fecha_recepcion, observacion, fk_id_radio, fk_id_tecnico));
        }

        resultSet.close();
        statement.close();

        disconnect();
        return mantenimientos;
    }

    public boolean deleteMantenimiento(int pk_id_mantenimiento) throws SQLException {
        String sql = "DELETE FROM tbl_mantenimiento WHERE pk_id_mantenimiento = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, pk_id_mantenimiento);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Optional<Mantenimiento> getMantenimiento(int pk_id_mantenimiento) throws SQLException {
        Mantenimiento mantenimiento = null;
        String sql = "SELECT * FROM tbl_mantenimiento WHERE pk_id_mantenimiento = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, pk_id_mantenimiento);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String tipo = resultSet.getString("Tipo");
            String descripcion = resultSet.getString("Descripción");
            Date fecha_recepcion = resultSet.getDate("Fecha_recepción");
            String observacion = resultSet.getString("Observación");
            Integer fk_id_radio = (Integer) resultSet.getObject("fk_id_radio");
            Integer fk_id_tecnico = (Integer) resultSet.getObject("fk_id_tecnico");

            mantenimiento = new Mantenimiento(pk_id_mantenimiento, tipo, descripcion, fecha_recepcion, observacion, fk_id_radio, fk_id_tecnico);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return Optional.ofNullable(mantenimiento);
    }

    // Implementa los métodos deleteMantenimiento, updateMantenimiento, y getMantenimiento siguiendo el patrón del UsuarioDAO.
}
