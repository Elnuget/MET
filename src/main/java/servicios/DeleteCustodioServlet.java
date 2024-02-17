package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO; // Asegúrate de que este DAO esté implementado para manejar operaciones de custodios

@WebServlet("/DeleteCustodioServlet")
public class DeleteCustodioServlet extends HttpServlet {

    private CustodioDAO custodioDao;

    public void init() {
        custodioDao = new CustodioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        try {
            boolean deleted = custodioDao.deleteCustodio(id);
            if (deleted) {
                // Opcional: Establecer un atributo con mensaje de éxito
                request.getSession().setAttribute("mensaje", "Custodio eliminado correctamente.");
            } else {
                // Opcional: Establecer un atributo con mensaje de error
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el custodio.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        response.sendRedirect("crudCustodio.jsp"); // Asegúrate de redireccionar a la página correcta
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteCustodioServlet handles the deletion of a custodian";
    }
}
