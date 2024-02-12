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
        String usuario = request.getParameter("Usuario");
        String contraseña = request.getParameter("Contraseña");
        String rol = request.getParameter("Rol");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setContraseña(contraseña);
        nuevoUsuario.setRol(rol);

        try {
            usuarioDao.insertUsuario(nuevoUsuario);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Pero no establecemos ningún atributo ni enviamos mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudUsuario.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddUsuarioServlet handles the addition of new users";
    }
}
