package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import modelos.Usuario;

@WebServlet("/LoadUsuarioServlet")
public class LoadUsuarioServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Usuario usuario = null;
        
        try {
            int id = Integer.parseInt(idStr);
            usuario = usuarioDao.getUsuario(id);
        } catch (NumberFormatException | NullPointerException e) {
            // Manejar excepción, por ejemplo, redirigir a una página de error
            response.sendRedirect("crudUsuario.jsp");
            return;
        } catch (Exception e) {
            // Manejar excepción de la base de datos
            e.printStackTrace();
        }

        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("edit-usuario.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "LoadUsuarioServlet carga la información del usuario para editar";
    }
}
