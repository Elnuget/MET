package servicios;

import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MantenimientoDAO;
import modelos.Mantenimiento;

@WebServlet("/ImprimirMantenimientoServlet")
public class ImprimirMantenimientoServlet extends HttpServlet {

    private MantenimientoDAO mantenimientoDao;

    public void init() {
        mantenimientoDao = new MantenimientoDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        try {
            Optional<Mantenimiento> mantenimientoOpt = mantenimientoDao.getMantenimiento(id);
            if (mantenimientoOpt.isPresent()) {
                Mantenimiento mantenimiento = mantenimientoOpt.get();
                request.setAttribute("mantenimiento", mantenimiento);
                request.getRequestDispatcher("/imprimirMantenimiento.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("mensaje", "Mantenimiento no encontrado.");
                response.sendRedirect("crudMantenimiento.jsp");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ImprimirMantenimientoServlet handles the printing of a maintenance record";
    }
}
