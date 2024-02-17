package dao;

import modelos.Radio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadioDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private Connection jdbcConnection;

    public RadioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertRadio(Radio radio) throws SQLException {
        String sql = "INSERT INTO tbl_radio (tipo, modelo, marca, serie, fk_id_custodio) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, radio.getTipo());
        statement.setString(2, radio.getModelo());
        statement.setString(3, radio.getMarca());
        statement.setString(4, radio.getSerie());
        if (radio.getFk_id_custodio() != null) {
            statement.setInt(5, radio.getFk_id_custodio());
        } else {
            statement.setNull(5, Types.INTEGER);
        }

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Radio> selectAllRadios() throws SQLException {
        List<Radio> radios = new ArrayList<>();
        String sql = "SELECT * FROM tbl_radio";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("pk_id_radio");
            String tipo = resultSet.getString("tipo");
            String modelo = resultSet.getString("modelo");
            String marca = resultSet.getString("marca");
            String serie = resultSet.getString("serie");
            Integer fk_id_custodio = (Integer) resultSet.getObject("fk_id_custodio");

            radios.add(new Radio(id, tipo, modelo, marca, serie, fk_id_custodio));
        }

        resultSet.close();
        statement.close();

        disconnect();
        return radios;
    }

    public boolean updateRadio(Radio radio) throws SQLException {
        String sql = "UPDATE tbl_radio SET tipo = ?, modelo = ?, marca = ?, serie = ?, fk_id_custodio = ? WHERE pk_id_radio = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, radio.getTipo());
        statement.setString(2, radio.getModelo());
        statement.setString(3, radio.getMarca());
        statement.setString(4, radio.getSerie());
        if (radio.getFk_id_custodio() != null) {
            statement.setInt(5, radio.getFk_id_custodio());
        } else {
            statement.setNull(5, Types.INTEGER);
        }
        statement.setInt(6, radio.getPk_id_radio());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteRadio(int pk_id_radio) throws SQLException {
        String sql = "DELETE FROM tbl_radio WHERE pk_id_radio = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, pk_id_radio);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Radio getRadio(int id) throws SQLException {
        Radio radio = null;
        String sql = "SELECT * FROM tbl_radio WHERE pk_id_radio = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String tipo = resultSet.getString("tipo");
            String modelo = resultSet.getString("modelo");
            String marca = resultSet.getString("marca");
            String serie = resultSet.getString("serie");
            Integer fk_id_custodio = (Integer) resultSet.getObject("fk_id_custodio");

            radio = new Radio(id, tipo, modelo, marca, serie, fk_id_custodio);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return radio;
    }
}
