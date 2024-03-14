package servicios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MantenimientoDAO;
import modelos.Mantenimiento;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/LoadMantenimientoServlet")
public class LoadMantenimientoServlet extends HttpServlet {
    private MantenimientoDAO mantenimientoDao;

    public void init() {
        this.mantenimientoDao = new MantenimientoDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Mantenimiento> mantenimientoOpt = mantenimientoDao.getMantenimiento(id);
            
            if (mantenimientoOpt.isPresent()) {
                request.setAttribute("mantenimiento", mantenimientoOpt.get());
                request.getRequestDispatcher("edit-mantenimiento.jsp").forward(request, response);
            } else {
                response.sendRedirect("crudMantenimiento.jsp");
            }
        } catch (Exception e) {
            throw new ServletException("Error al cargar datos de mantenimiento", e);
        }
    }
}
