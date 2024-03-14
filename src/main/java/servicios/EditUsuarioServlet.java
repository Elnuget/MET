package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import modelos.Usuario;

@WebServlet("/EditUsuarioServlet")
public class EditUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Asegúrate de tener este campo en tu formulario de edición
        String usuario = request.getParameter("Usuario");
        String contraseña = request.getParameter("Contraseña");
        String rol = request.getParameter("rol");

        Usuario usuarioActualizado = new Usuario(id, usuario, contraseña, rol);

        try {
            usuarioDao.updateUsuario(usuarioActualizado);
            response.sendRedirect("crudUsuario.jsp"); // O a una página de confirmación si prefieres
        } catch (Exception e) {
            // Trata el error adecuadamente
            e.printStackTrace();
            response.getWriter().println("Error al actualizar el usuario: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "EditUsuarioServlet handles the editing of an existing user";
    }
}
