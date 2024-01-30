package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configura el tipo de contenido y codificación de caracteres para la respuesta
        response.setContentType("text/html;charset=UTF-8");

        // Obtiene la sesión actual y la invalida
        HttpSession session = request.getSession(false); // false significa que no se creará una nueva sesión si no existe
        if (session != null) {
            session.invalidate(); // Invalida la sesión si existe
        }

        // Redirige al usuario a la página de inicio de sesión (index.html)
        response.sendRedirect("index.html");
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
        return "LogoutServlet invalida la sesión y redirige al usuario a la página de inicio de sesión";
    }
}
