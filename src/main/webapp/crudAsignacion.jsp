<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Asignaciones</title>
    <!-- Incluir Bootstrap CSS para el estilo -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Gestión de Asignaciones</h1>

        <!-- Formulario para añadir una nueva asignación -->
        <form action="AddAsignacionServlet" method="POST">
            <div class="form-group">
                <label for="fechaAsignacion">Fecha de Asignación:</label>
                <input type="datetime-local" id="fechaAsignacion" name="fechaAsignacion" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="idTecnico">ID del Técnico:</label>
                <input type="number" id="idTecnico" name="idTecnico" class="form-control" placeholder="ID del Técnico" required>
            </div>
            <div class="form-group">
                <label for="idRadio">ID del Radio:</label>
                <input type="number" id="idRadio" name="idRadio" class="form-control" placeholder="ID del Radio" required>
            </div>
            <button type="submit" class="btn btn-success">Añadir Asignación</button>
        </form>

        <hr>

        <!-- Lista de asignaciones existentes -->
        <h2>Asignaciones Existentes</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID Asignación</th>
                    <th>Fecha Asignación</th>
                    <th>ID Técnico</th>
                    <th>ID Radio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        String driver = "com.mysql.cj.jdbc.Driver";
                        String connectionUrl = "jdbc:mysql://localhost:3306/met"; // Asegúrate de que esta es tu URL de base de datos
                        String dbUser = "root";
                        String dbPassword = ""; // Poner tu contraseña si es necesario

                        Class.forName(driver);
                        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM tbl_asignacion"; // Asegúrate de que esta es tu tabla de asignaciones
                        rs = stmt.executeQuery(sql);

                        while (rs.next()) {
                            int id = rs.getInt("pk_id_asignacion");
                            Timestamp fechaAsignacion = rs.getTimestamp("Fecha_asignacion");
                            int idTecnico = rs.getInt("fk_id_tecnico");
                            int idRadio = rs.getInt("fk_id_radio");
                %>
                <tr>
                    <td><%= id %></td>
                    <td><%= fechaAsignacion %></td>
                    <td><%= idTecnico %></td>
                    <td><%= idRadio %></td>
                    <td>
                        <a href="EditAsignacionServlet?id=<%= id %>" class="btn btn-primary">Editar</a>
                        <a href="DeleteAsignacionServlet?id=<%= id %>" class="btn btn-danger">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                        if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                        if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                    }
                %>
            </tbody>
        </table>

    </div>
    <!-- Bootstrap JS y dependencias -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
