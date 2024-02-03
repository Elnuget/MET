package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustodioDAO;

@WebServlet("/DeleteCustodioServlet")
public class DeleteCustodioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            CustodioDAO custodioDao = new CustodioDAO();
            
            boolean success = custodioDao.deleteCustodio(id);
            
            if(success){
                request.setAttribute("mensaje", "Custodio eliminado correctamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el custodio.");
            }
        }
        request.getRequestDispatcher("crudCustodio.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteCustodioServlet handles custodio deletion";
    }
}
