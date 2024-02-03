package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO;
import modelos.Custodio;

@WebServlet(name = "AddEditCustodioServlet", urlPatterns = {"/AddEditCustodioServlet"})
public class AddEditCustodioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Recoge los datos del formulario
        String idStr = request.getParameter("id");
        String nombres = request.getParameter("nombres");
        String cedula = request.getParameter("cedula");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String subzona = request.getParameter("subzona");
        String distrito = request.getParameter("distrito");

        CustodioDAO custodioDao = new CustodioDAO();
        Custodio custodio = new Custodio();

        // Asigna los valores al objeto custodio
        if (idStr != null && !idStr.isEmpty()) {
            custodio.setId(Integer.parseInt(idStr));
        }
        custodio.setNombres(nombres);
        custodio.setCedula(cedula);
        custodio.setCelular(celular);
        custodio.setDireccion(direccion);
        custodio.setCorreo(correo);
        custodio.setSubzona(subzona);
        custodio.setDistrito(distrito);

        // Decide si agregar o actualizar basado en la presencia del ID
        if (custodio.getId() > 0) {
            custodioDao.updateCustodio(custodio);
            request.setAttribute("mensaje", "El custodio ha sido actualizado correctamente.");
        } else {
            custodioDao.addCustodio(custodio);
            request.setAttribute("mensaje", "El custodio ha sido insertado correctamente.");
        }

        request.getRequestDispatcher("crudCustodio.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddEditCustodioServlet handles both adding and editing custodian entries";
    }
}
