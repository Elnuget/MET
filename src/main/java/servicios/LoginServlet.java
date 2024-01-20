package servicios;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            // Aquí comienza la lógica de validación
            if ("Javier".equals(username) && "1234".equals(password)) {
                // Si el usuario y la contraseña son correctos
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Bienvenido</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login Exitoso</h1>");

// Tu contenido adicional iría aquí...
// Mensaje de proyecto en producción
                out.println("<footer>");
                out.println("<hr>");
                out.println("<p><strong>Nota:</strong> Este sistema está en un entorno de producción. Para reportar cualquier problema, por favor contacte al administrador del sistema.</p>");
                out.println("</footer>");

                out.println("</body>");
                out.println("</html>");

            } else {
                // Si el usuario o la contraseña son incorrectos
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error de Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Credenciales Incorrectas</h1>");
                out.println("</body>");
                out.println("</html>");
            }
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
