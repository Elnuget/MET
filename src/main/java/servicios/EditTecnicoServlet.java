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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar el ID del técnico desde el parámetro de la solicitud
        String idStr = request.getParameter("pk_id_tecnico");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        // Usar TecnicoDAO para encontrar el técnico por su ID
        Tecnico tecnico = tecnicoDao.selectTecnico(id);

        // Si se encuentra el técnico, establecerlo como un atributo de la solicitud y reenviarlo al JSP de edición
        if (tecnico != null) {
            request.setAttribute("tecnico", tecnico);
            request.getRequestDispatcher("/edit-tecnico.jsp").forward(request, response);
        } else {
            // Si no se encuentra el técnico, opcionalmente puedes manejar este caso (por ejemplo, redirigiendo a una página de error o lista de técnicos)
            response.sendRedirect("crudTecnico.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "EditTecnicoServlet handles the loading of a technician's details for editing";
    }
}
