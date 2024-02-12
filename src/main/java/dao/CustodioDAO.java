package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Custodio;

public class CustodioDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_CUSTODIO_SQL = "INSERT INTO tbl_custodio" +
        " (Nombres, Cedula, Celular, Direccion, Correo, Subzona, Distrito) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTODIO_BY_ID = "SELECT * FROM tbl_custodio WHERE pk_id_custodio =?";
    private static final String SELECT_ALL_CUSTODIOS = "SELECT * FROM tbl_custodio";
    private static final String DELETE_CUSTODIO_SQL = "DELETE FROM tbl_custodio WHERE pk_id_custodio = ?;";
    private static final String UPDATE_CUSTODIO_SQL = "UPDATE tbl_custodio SET Nombres = ?, Cedula = ?, Celular = ?, " +
        "Direccion = ?, Correo = ?, Subzona = ?, Distrito = ? WHERE pk_id_custodio = ?;";

    public CustodioDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addCustodio(Custodio custodio) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTODIO_SQL)) {
            preparedStatement.setString(1, custodio.getNombres());
            preparedStatement.setString(2, custodio.getCedula());
            preparedStatement.setString(3, custodio.getCelular());
            preparedStatement.setString(4, custodio.getDireccion());
            preparedStatement.setString(5, custodio.getCorreo());
            preparedStatement.setString(6, custodio.getSubzona());
            preparedStatement.setString(7, custodio.getDistrito());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Custodio selectCustodio(int id) {
        Custodio custodio = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTODIO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombres = rs.getString("Nombres");
                String cedula = rs.getString("Cedula");
                String celular = rs.getString("Celular");
                String direccion = rs.getString("Direccion");
                String correo = rs.getString("Correo");
                String subzona = rs.getString("Subzona");
                String distrito = rs.getString("Distrito");
                custodio = new Custodio(id, nombres, cedula, celular, direccion, correo, subzona, distrito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custodio;
    }

    public List<Custodio> selectAllCustodios() {
        List<Custodio> custodios = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTODIOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("pk_id_custodio");
                String nombres = rs.getString("Nombres");
                String cedula = rs.getString("Cedula");
                String celular = rs.getString("Celular");
                String direccion = rs.getString("Direccion");
                String correo = rs.getString("Correo");
                String subzona = rs.getString("Subzona");
                String distrito = rs.getString("Distrito");
                custodios.add(new Custodio(id, nombres, cedula, celular, direccion, correo, subzona, distrito));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custodios;
    }

    public boolean deleteCustodio(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTODIO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateCustodio(Custodio custodio) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTODIO_SQL)) {
            statement.setString(1, custodio.getNombres());
            statement.setString(2, custodio.getCedula());
            statement.setString(3, custodio.getCelular());
            statement.setString(4, custodio.getDireccion());
            statement.setString(5, custodio.getCorreo());
            statement.setString(6, custodio.getSubzona());
            statement.setString(7, custodio.getDistrito());
            statement.setInt(8, custodio.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}