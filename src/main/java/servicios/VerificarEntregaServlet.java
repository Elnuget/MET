package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.EntregaDAO;
import modelos.Entrega; // Importa el modelo de Entrega

@WebServlet("/VerificarEntregaServlet")
public class VerificarEntregaServlet extends HttpServlet {
    private EntregaDAO entregaDao;

    @Override
    public void init() {
        entregaDao = new EntregaDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int fkIdMantenimiento = Integer.parseInt(request.getParameter("fkIdMantenimiento"));

            // Intenta obtener la entrega por el ID de mantenimiento
            Entrega entrega = entregaDao.obtenerEntregaPorMantenimiento(fkIdMantenimiento);

            if (entrega != null) {
                // Si existe una entrega, pasa la información al JSP
                request.setAttribute("entrega", entrega);
                request.getRequestDispatcher("/mensajeEntregaExistente.jsp").forward(request, response);
            } else {
                // Si no existe una entrega, redirige al formulario para añadir una nueva
                response.sendRedirect("add-entrega.jsp?fkIdMantenimiento=" + fkIdMantenimiento);
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace();
        }
    }
}
