package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO;
import modelos.Custodio;

@WebServlet("/AddCustodioServlet")
public class AddCustodioServlet extends HttpServlet {
    private CustodioDAO custodioDao;

    public void init() {
        custodioDao = new CustodioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombres = request.getParameter("Nombres");
        String cedula = request.getParameter("Cedula");
        String celular = request.getParameter("Celular");
        String direccion = request.getParameter("Direccion");
        String correo = request.getParameter("Correo");
        String subzona = request.getParameter("Subzona");
        String distrito = request.getParameter("Distrito");

        Custodio nuevoCustodio = new Custodio();
        nuevoCustodio.setNombres(nombres);
        nuevoCustodio.setCedula(cedula);
        nuevoCustodio.setCelular(celular);
        nuevoCustodio.setDireccion(direccion);
        nuevoCustodio.setCorreo(correo);
        nuevoCustodio.setSubzona(subzona);
        nuevoCustodio.setDistrito(distrito);

        try {
            custodioDao.addCustodio(nuevoCustodio);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Por simplicidad, este ejemplo no establece ningún atributo ni envía mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudCustodio.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddCustodioServlet handles the addition of new custodians";
    }
}
