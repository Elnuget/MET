package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TecnicoDAO;
import modelos.Tecnico;

@WebServlet("/LoadTecnicoServlet")
public class LoadTecnicoServlet extends HttpServlet {
    private TecnicoDAO tecnicoDao;

    public void init() {
        tecnicoDao = new TecnicoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tecnico tecnico = null;
        try {
            tecnico = tecnicoDao.selectTecnico(id);
        } catch (Exception e) {
            // Log the exception
        }

        request.setAttribute("tecnico", tecnico);
        request.getRequestDispatcher("edit-tecnico.jsp").forward(request, response);
    }
}
