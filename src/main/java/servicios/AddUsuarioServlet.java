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
        usuarioDao = new UsuarioDAO("jdbc:mysql://localhost:3306/miBaseDatos", "miUsuario", "miContraseña");

 // Asegúrate de pasar los parámetros necesarios si los hay
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simplemente reenvía la solicitud al formulario JSP donde el usuario puede ingresar los detalles del nuevo usuario
        request.getRequestDispatcher("/usuario-add.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña"); // Considera usar hashing para la contraseña antes de almacenarla
        String rol = request.getParameter("rol");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setContraseña(contraseña); // Asegúrate de hash la contraseña antes de establecerla aquí
        nuevoUsuario.setRol(rol);

        try {
            boolean insertResult = usuarioDao.insertUsuario(nuevoUsuario);
            if(insertResult) {
                // Opcional: establece un mensaje de éxito para mostrar en la vista
                request.setAttribute("mensaje", "Usuario agregado con éxito.");
            } else {
                // Opcional: establece un mensaje de error para mostrar en la vista
                request.setAttribute("mensaje", "No se pudo agregar el usuario.");
            }
        } catch (Exception e) {
            // Manejo de la excepción y establecimiento de un mensaje de error
            request.setAttribute("mensaje", "Error al agregar el usuario: " + e.getMessage());
        }

        // Redirige o reenvía a la vista que mostrará el resultado de la operación
        request.getRequestDispatcher("/usuario-add.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddUsuarioServlet handles the addition of new users";
    }
}
