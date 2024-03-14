package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO;
import modelos.Custodio;

@WebServlet("/EditCustodioServlet")
public class EditCustodioServlet extends HttpServlet {
    
    private CustodioDAO custodioDao;

    public void init() {
        custodioDao = new CustodioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("Nombres");
        String cedula = request.getParameter("Cedula");
        String celular = request.getParameter("Celular");
        String direccion = request.getParameter("Direccion");
        String correo = request.getParameter("Correo");
        String subzona = request.getParameter("Subzona");
        String distrito = request.getParameter("Distrito");
        String rango = request.getParameter("Rango");

        Custodio custodio = new Custodio(id, nombres, cedula, celular, direccion, correo, subzona, distrito, rango);

        try {
            custodioDao.updateCustodio(custodio);
            response.sendRedirect("crudCustodio.jsp");
        } catch (Exception e) {
            // Log the exception
            throw new ServletException("Error al actualizar custodio", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "EditCustodioServlet handles updating custodian information";
    }
}
