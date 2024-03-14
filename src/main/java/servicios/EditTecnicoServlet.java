package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TecnicoDAO;
import modelos.Tecnico;

@WebServlet("/EditTecnicoServlet")
public class EditTecnicoServlet extends HttpServlet {

    private TecnicoDAO tecnicoDao;

    public void init() {
        tecnicoDao = new TecnicoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("Nombres");
        String cedula = request.getParameter("Cedula");
        String celular = request.getParameter("Celular");

        Tecnico tecnico = new Tecnico(id, nombres, cedula, celular);

        try {
            tecnicoDao.updateTecnico(tecnico);
            response.sendRedirect("crudTecnico.jsp");
        } catch (Exception e) {
            // Log the exception
            throw new ServletException("Error al actualizar técnico", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "EditTecnicoServlet handles updating technician information";
    }
}
