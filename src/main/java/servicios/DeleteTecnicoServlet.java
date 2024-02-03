package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TecnicoDAO;

@WebServlet("/DeleteTecnicoServlet")
public class DeleteTecnicoServlet extends HttpServlet {

    private TecnicoDAO tecnicoDao;

    public void init() {
        tecnicoDao = new TecnicoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        try {
            boolean deleted = tecnicoDao.deleteTecnico(id);
            if (deleted) {
                // Opcional: Puedes establecer un atributo con mensaje de éxito para mostrar en la página
                request.getSession().setAttribute("mensaje", "Técnico eliminado correctamente.");
            } else {
                // Opcional: Puedes establecer un atributo con mensaje de error si la eliminación falló
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el técnico.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        response.sendRedirect("crudTecnico.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteTecnicoServlet handles the deletion of a technician";
    }
}
