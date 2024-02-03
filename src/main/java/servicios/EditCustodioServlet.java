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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Custodio existingCustodio = custodioDao.selectCustodio(id);
        
        // Establecer el custodio en el request para que pueda ser accesible en el JSP
        request.setAttribute("custodio", existingCustodio);
        
        // Reenviar a la página de edición
        request.getRequestDispatcher("/custodio-edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Este método puede manejar la actualización del custodio si decides implementarlo aquí
        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String cedula = request.getParameter("cedula");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String subzona = request.getParameter("subzona");
        String distrito = request.getParameter("distrito");
        
        Custodio custodio = new Custodio(id, nombres, cedula, celular, direccion, correo, subzona, distrito);
        
        boolean success = custodioDao.updateCustodio(custodio);
        
        if(success) {
            request.setAttribute("mensaje", "Custodio actualizado correctamente.");
        } else {
            request.setAttribute("mensaje", "No se pudo actualizar el custodio.");
        }
        
        request.getRequestDispatcher("crudCustodio.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "EditCustodioServlet handles editing custodio details";
    }
}
