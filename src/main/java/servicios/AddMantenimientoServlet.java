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

@WebServlet("/AddMantenimientoServlet")
public class AddMantenimientoServlet extends HttpServlet {
    private MantenimientoDAO mantenimientoDao;

    public void init() {
        mantenimientoDao = new MantenimientoDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("Tipo");
        String descripcion = request.getParameter("Descripción");
        String fechaRecepcion = request.getParameter("Fecha_recepción");
        String fechaEntrega = request.getParameter("Fecha_entrega");
        String observacion = request.getParameter("Observación");
        int fk_id_radio = Integer.parseInt(request.getParameter("fk_id_radio"));
        int fk_id_tecnico = Integer.parseInt(request.getParameter("fk_id_tecnico"));

        Mantenimiento nuevoMantenimiento = new Mantenimiento();
        nuevoMantenimiento.setTipo(tipo);
        nuevoMantenimiento.setDescripcion(descripcion);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            nuevoMantenimiento.setFecha_recepcion(dateFormat.parse(fechaRecepcion));
            nuevoMantenimiento.setFecha_entrega(dateFormat.parse(fechaEntrega));
        } catch (Exception e) {
            // Manejo de error en caso de falla al parsear las fechas
        }

        nuevoMantenimiento.setObservacion(observacion);
        nuevoMantenimiento.setFk_id_radio(fk_id_radio);
        nuevoMantenimiento.setFk_id_tecnico(fk_id_tecnico);

        try {
            mantenimientoDao.insertMantenimiento(nuevoMantenimiento);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Por simplicidad, este ejemplo no establece ningún atributo ni envía mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudMantenimiento.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddMantenimientoServlet handles the addition of new maintenance records";
    }
}
