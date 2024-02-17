package servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.EntregaDAO;
import modelos.Entrega;

@WebServlet("/AddEntregaServlet")
public class AddEntregaServlet extends HttpServlet {
    private EntregaDAO entregaDao;

    public void init() {
        entregaDao = new EntregaDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fechaEntrega = request.getParameter("fechaEntrega");
            String observaciones = request.getParameter("observaciones");
            int fkIdMantenimiento = Integer.parseInt(request.getParameter("fkIdMantenimiento"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(fechaEntrega);

            Entrega nuevaEntrega = new Entrega();
            nuevaEntrega.setFecha_entrega(fecha);
            nuevaEntrega.setObservaciones(observaciones);
            nuevaEntrega.setFk_id_mantenimiento(fkIdMantenimiento);

            entregaDao.insertEntrega(nuevaEntrega);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Pero no establecemos ningún atributo ni enviamos mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudMantenimiento.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddEntregaServlet handles the addition of new deliveries";
    }
}
