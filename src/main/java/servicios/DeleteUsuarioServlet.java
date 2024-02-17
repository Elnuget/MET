package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;

@WebServlet("/DeleteUsuarioServlet")
public class DeleteUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        try {
            boolean deleted = usuarioDao.deleteUsuario(id);
            if (deleted) {
                // Opcional: Puedes establecer un atributo con mensaje de éxito para mostrar en la página
                request.getSession().setAttribute("mensaje", "Usuario eliminado correctamente.");
            } else {
                // Opcional: Puedes establecer un atributo con mensaje de error si la eliminación falló
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el usuario.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        response.sendRedirect("crudUsuario.jsp"); // Asegúrate de redireccionar a la página correcta
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteUsuarioServlet handles the deletion of a user";
    }
}
