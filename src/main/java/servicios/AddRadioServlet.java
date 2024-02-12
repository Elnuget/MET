package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RadioDAO;
import modelos.Radio;

@WebServlet("/AddRadioServlet")
public class AddRadioServlet extends HttpServlet {
    private RadioDAO radioDao;

    public void init() {
        radioDao = new RadioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String serie = request.getParameter("serie");
        int fk_id_custodio = Integer.parseInt(request.getParameter("fk_id_custodio"));

        Radio nuevaRadio = new Radio();
        nuevaRadio.setTipo(tipo);
        nuevaRadio.setModelo(modelo);
        nuevaRadio.setMarca(marca);
        nuevaRadio.setSerie(serie);
        nuevaRadio.setFk_id_custodio(fk_id_custodio);

        try {
            radioDao.insertRadio(nuevaRadio);
        } catch (Exception e) {
            // Aquí podrías registrar el error con un sistema de logging
            // Por simplicidad, este ejemplo no establece ningún atributo ni envía mensaje al cliente
        }

        // Redirección a la página deseada después de la inserción
        // Esto evita que la recarga de la página vuelva a enviar los datos
        response.sendRedirect("crudRadio.jsp");
    }

    @Override
    public String getServletInfo() {
        return "AddRadioServlet handles the addition of new radios";
    }
}
