package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MantenimientoDAO; // Asegúrate de que este DAO esté implementado para manejar operaciones de mantenimiento

@WebServlet("/DeleteMantenimientoServlet")
public class DeleteMantenimientoServlet extends HttpServlet {

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
            boolean deleted = mantenimientoDao.deleteMantenimiento(id);
            if (deleted) {
                // Opcional: Establecer un atributo con mensaje de éxito
                request.getSession().setAttribute("mensaje", "Mantenimiento eliminado correctamente.");
            } else {
                // Opcional: Establecer un atributo con mensaje de error
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el mantenimiento.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        response.sendRedirect("crudMantenimiento.jsp"); // Asegúrate de redireccionar a la página correcta
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteMantenimientoServlet handles the deletion of a maintenance record";
    }
}
