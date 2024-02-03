package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TecnicoDAO;
import modelos.Tecnico;

@WebServlet("/AddEditTecnicoServlet")
public class AddEditTecnicoServlet extends HttpServlet {

    private TecnicoDAO tecnicoDao;

    public void init() {
        tecnicoDao = new TecnicoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombres = request.getParameter("nombres");
        String cedula = request.getParameter("cedula");
        String celular = request.getParameter("celular");
        
        Tecnico tecnico = new Tecnico();
        tecnico.setNombres(nombres);
        tecnico.setCedula(cedula);
        tecnico.setCelular(celular);

        try {
            // Si el ID no está presente o es vacío, se trata de una adición.
            if (idStr == null || idStr.isEmpty()) {
                tecnicoDao.insertTecnico(tecnico);
            } else {
                // Si el ID está presente, se trata de una actualización.
                int id = Integer.parseInt(idStr);
                tecnico.setId(id);
                tecnicoDao.updateTecnico(tecnico);
            }
            response.sendRedirect("crudTecnico.jsp");
        } catch (Exception e) {
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "AddEditTecnicoServlet handles both adding and editing technician details";
    }
}
