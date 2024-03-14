package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO;
import modelos.Custodio;

@WebServlet("/LoadCustodioServlet")
public class LoadCustodioServlet extends HttpServlet {

    private CustodioDAO custodioDao;

    public void init() {
        custodioDao = new CustodioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Custodio custodio = null;
        try {
            custodio = custodioDao.selectCustodio(id);
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        request.setAttribute("custodio", custodio);
        request.getRequestDispatcher("edit-custodio.jsp").forward(request, response);
    }
}
