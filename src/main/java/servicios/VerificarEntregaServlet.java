package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.EntregaDAO;

@WebServlet("/VerificarEntregaServlet")
public class VerificarEntregaServlet extends HttpServlet {
    private EntregaDAO entregaDao;

    public void init() {
        entregaDao = new EntregaDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int fkIdMantenimiento = Integer.parseInt(request.getParameter("fkIdMantenimiento"));

            if (entregaDao.existeEntregaPorMantenimiento(fkIdMantenimiento)) {
                // Existe entrega, enviar a mensaje
                request.setAttribute("mensaje", "Ya se realizó la entrega para este mantenimiento.");
                request.getRequestDispatcher("/mensajeEntregaExistente.jsp").forward(request, response);
            } else {
                // No existe entrega, redirigir al formulario de añadir entrega
                response.sendRedirect("add-entrega.jsp?fkIdMantenimiento=" + fkIdMantenimiento);
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace();
        }
    }
}
