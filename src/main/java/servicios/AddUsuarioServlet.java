package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import modelos.Usuario;

@WebServlet("/AddUsuarioServlet")
public class AddUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Usuario = request.getParameter("Usuario");
        String Contraseña = request.getParameter("Contraseña");
        String rol = request.getParameter("rol");

        // Validación y asignación de valor por defecto para 'rol'
        if (rol == null || rol.isEmpty()) {
            rol = "valorPorDefecto"; // Reemplaza 'valorPorDefecto' con un valor válido para tu aplicación
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(Usuario);
        nuevoUsuario.setContraseña(Contraseña);
        nuevoUsuario.setRol(rol);

        try {
            usuarioDao.insertUsuario(nuevoUsuario);
            response.sendRedirect("crudUsuario.jsp");
        } catch (Exception e) {
            response.getWriter().println("Error al agregar el usuario: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "AddUsuarioServlet handles the addition of new users";
    }
}
