package servicios;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String connectionUrl = "jdbc:mysql://localhost:3306/met";
            String dbUser = "root";
            String dbPassword = "";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
            String sql = "SELECT * FROM tbl_usuario WHERE Usuario = ? AND Contraseña = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Usuario y contraseña correctos
                request.getSession().setAttribute("usuarioLogueado", username); // Establecer atributo de sesión
                response.sendRedirect("home.jsp"); // Redirige a home.jsp
            } else {
                // Usuario o contraseña incorrectos
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Error de Login</title>");
                    out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='container mt-5'>");
                    out.println("<div class='row'>");
                    out.println("<div class='col-md-6 mx-auto'>");
                    out.println("<div class='card card-body text-center'>");
                    out.println("<h1 class='card-title mb-3'>Credenciales Incorrectas</h1>");
                    out.println("<p class='card-text'><a href='index.html' class='btn btn-primary'>Volver a intentarlo</a></p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new ServletException("Error de Login", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}
