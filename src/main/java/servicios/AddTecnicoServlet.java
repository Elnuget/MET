package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TecnicoDAO;
import modelos.Tecnico;

@WebServlet("/AddTecnicoServlet")
public class AddTecnicoServlet extends HttpServlet {
    private TecnicoDAO tecnicoDao;

    public void init() {
        tecnicoDao = new TecnicoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombres = request.getParameter("Nombres");
        String cedula = request.getParameter("Cedula");
        String celular = request.getParameter("Celular");

        Tecnico nuevoTecnico = new Tecnico();
        nuevoTecnico.setNombres(nombres);
        nuevoTecnico.setCedula(cedula);
        nuevoTecnico.setCelular(celular);

        try {
            tecnicoDao.insertTecnico(nuevoTecnico);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Por simplicidad, este ejemplo no establece ningún atributo ni envía mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudTecnico.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddTecnicoServlet handles the addition of new technicians";
    }
}
