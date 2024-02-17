package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RadioDAO; // Asegúrate de que este DAO esté implementado para manejar operaciones de radios

@WebServlet("/DeleteRadioServlet")
public class DeleteRadioServlet extends HttpServlet {

    private RadioDAO radioDao;

    public void init() {
        radioDao = new RadioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        try {
            boolean deleted = radioDao.deleteRadio(id);
            if (deleted) {
                // Opcional: Establecer un atributo con mensaje de éxito
                request.getSession().setAttribute("mensaje", "Radio eliminado correctamente.");
            } else {
                // Opcional: Establecer un atributo con mensaje de error
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el radio.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        response.sendRedirect("crudRadio.jsp"); // Asegúrate de redireccionar a la página correcta
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteRadioServlet handles the deletion of a radio";
    }
}
