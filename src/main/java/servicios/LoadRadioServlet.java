package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RadioDAO;
import modelos.Radio;

@WebServlet("/LoadRadioServlet")
public class LoadRadioServlet extends HttpServlet {
    private RadioDAO radioDao;

    public void init() {
        radioDao = new RadioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Radio radio = radioDao.getRadio(id);
            request.setAttribute("radio", radio);
            request.getRequestDispatcher("edit-radio.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar datos de la radio", e);
        }
    }
}
