<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta de base de datos</title>
</head>
<body>

<%
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        String driver = "com.mysql.cj.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/met";
        String dbUser = "root";
        String dbPassword = "";

        Class.forName(driver);
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
        String sql = "SELECT * FROM tbl_usuario"; // Asegúrate que el nombre de la tabla sea correcto
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();

        out.println("<table border='1'>");
        out.println("<tr><th>Usuario</th><th>Contraseña</th></tr>");
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getString("Usuario") + "</td>"); // Asegúrate de que estas columnas existan en tu tabla
            out.println("<td>" + rs.getString("Contraseña") + "</td>"); // Asegúrate de que estas columnas existan en tu tabla
            out.println("</tr>");
        }
        out.println("</table>");
    } catch (Exception e) {
        out.println("<h1>Ocurrió un error:</h1>");
        e.printStackTrace(new PrintWriter(out, true)); // El segundo argumento 'true' es para auto-flush
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(new PrintWriter(out, true)); }
        if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(new PrintWriter(out, true)); }
        if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(new PrintWriter(out, true)); }
    }
%>

</body>
</html>
