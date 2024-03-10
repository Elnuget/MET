package servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MantenimientoDAO;
import java.io.PrintWriter;
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
        String observacion = request.getParameter("Observación");
        int fk_id_radio = Integer.parseInt(request.getParameter("fk_id_radio"));
        int fk_id_tecnico = Integer.parseInt(request.getParameter("fk_id_tecnico"));
        String fk_id_usuario = request.getParameter("fk_id_usuario");

        Mantenimiento nuevoMantenimiento = new Mantenimiento();
        nuevoMantenimiento.setTipo(tipo);
        nuevoMantenimiento.setDescripcion(descripcion);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            nuevoMantenimiento.setFecha_recepcion(dateFormat.parse(fechaRecepcion));

        } catch (Exception e) {
            // Manejo de error en caso de falla al parsear las fechas
        }

        nuevoMantenimiento.setObservacion(observacion);
        nuevoMantenimiento.setFk_id_radio(fk_id_radio);
        nuevoMantenimiento.setFk_id_tecnico(fk_id_tecnico);
        nuevoMantenimiento.setFk_id_usuario(fk_id_usuario);

        try {
            mantenimientoDao.insertMantenimiento(nuevoMantenimiento);
            response.sendRedirect("crudMantenimiento.jsp");
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8"); // Establece el tipo de contenido de la respuesta
            try (PrintWriter out = response.getWriter()) {
                // Inicio del HTML en la respuesta
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error al insertar mantenimiento</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error al procesar la solicitud de mantenimiento</h1>");
                out.println("<p>Ocurrió un error al intentar insertar el registro de mantenimiento: " + e.getMessage() + "</p>");
                // Proporciona un enlace para volver a la página anterior o al inicio
                out.println("<a href='crudMantenimiento.jsp'>Volver</a>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException ioException) {
                ioException.printStackTrace(); // En un escenario real, deberías manejar este error adecuadamente.
            }
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
    }

    @Override
    public String getServletInfo() {
        return "AddMantenimientoServlet handles the addition of new maintenance records";
    }
}
