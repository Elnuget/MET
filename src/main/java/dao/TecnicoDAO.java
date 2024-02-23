package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Tecnico;

public class TecnicoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/met";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_TECNICO_SQL = "INSERT INTO tbl_tecnico"
            + "  (nombres, cedula, celular) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_TECNICO_BY_ID = "SELECT * FROM tbl_tecnico WHERE pk_id_tecnico =?";
    private static final String SELECT_ALL_TECNICOS = "SELECT * FROM tbl_tecnico";
    private static final String DELETE_TECNICO_SQL = "DELETE FROM tbl_tecnico WHERE pk_id_tecnico = ?;";
    private static final String UPDATE_TECNICO_SQL = "UPDATE tbl_tecnico SET Nombres = ?, Cedula = ?, Celular = ? WHERE pk_id_tecnico = ?;";

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

    public void insertTecnico(Tecnico tecnico) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TECNICO_SQL)) {
            preparedStatement.setString(1, tecnico.getNombres());
            preparedStatement.setString(2, tecnico.getCedula());
            preparedStatement.setString(3, tecnico.getCelular());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Tecnico selectTecnico(int id) {
        Tecnico tecnico = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TECNICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombres = rs.getString("nombres");
                String cedula = rs.getString("cedula");
                String celular = rs.getString("celular");
                tecnico = new Tecnico(id, nombres, cedula, celular);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tecnico;
    }

    public List<Tecnico> selectAllTecnicos() {
        List<Tecnico> tecnicos = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TECNICOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("pk_id_tecnico");
                String nombres = rs.getString("Nombres");
                String cedula = rs.getString("Cedula");
                String celular = rs.getString("Celular");
                tecnicos.add(new Tecnico(id, nombres, cedula, celular));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tecnicos;
    }

    public boolean deleteTecnico(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TECNICO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTecnico(Tecnico tecnico) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TECNICO_SQL)) {
            statement.setString(1, tecnico.getNombres());
            statement.setString(2, tecnico.getCedula());
            statement.setString(3, tecnico.getCelular());
            statement.setInt(4, tecnico.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public Tecnico findtecnicoById(int pk_id_tecnico) throws SQLException {
        Tecnico tecnico = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TECNICO_BY_ID)) {
            preparedStatement.setInt(1, pk_id_tecnico);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombres = rs.getString("nombres");
                String cedula = rs.getString("cedula");
                String celular = rs.getString("celular");
                tecnico = new Tecnico(pk_id_tecnico, nombres, cedula, celular);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tecnico;
    }
}
