package servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MantenimientoDAO;
import modelos.Mantenimiento;

@WebServlet("/EditMantenimientoServlet")
public class EditMantenimientoServlet extends HttpServlet {
    private MantenimientoDAO mantenimientoDao;

    public void init() {
        mantenimientoDao = new MantenimientoDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("Tipo");
        String descripcion = request.getParameter("Descripcion");
        String fechaRecepcionStr = request.getParameter("Fecha_recepcion");
        String observacion = request.getParameter("Observacion");
        Integer fk_id_radio = Integer.parseInt(request.getParameter("fk_id_radio"));
        Integer fk_id_tecnico = Integer.parseInt(request.getParameter("fk_id_tecnico"));
        String fk_id_usuario = request.getParameter("fk_id_usuario");
        
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setPk_id_mantenimiento(id);
        mantenimiento.setTipo(tipo);
        mantenimiento.setDescripcion(descripcion);
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mantenimiento.setFecha_recepcion(dateFormat.parse(fechaRecepcionStr));
        } catch (Exception e) {
            // Manejo de error en caso de falla al parsear las fechas
            e.printStackTrace();
        }
        
        mantenimiento.setObservacion(observacion);
        mantenimiento.setFk_id_radio(fk_id_radio);
        mantenimiento.setFk_id_tecnico(fk_id_tecnico);
        mantenimiento.setFk_id_usuario(fk_id_usuario);
        try {
            mantenimientoDao.updateMantenimiento(mantenimiento);
            response.sendRedirect("crudMantenimiento.jsp");
        } catch (Exception e) {
            throw new ServletException("Error al editar mantenimiento", e);
        }
    }
}
