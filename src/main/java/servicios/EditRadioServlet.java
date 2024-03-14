package servicios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RadioDAO;
import modelos.Radio;

@WebServlet("/EditRadioServlet")
public class EditRadioServlet extends HttpServlet {
    private RadioDAO radioDao;

    public void init() {
        radioDao = new RadioDAO("jdbc:mysql://localhost:3306/met", "root", "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String tipo = request.getParameter("tipo");
            String modelo = request.getParameter("modelo");
            String marca = request.getParameter("marca");
            String serie = request.getParameter("serie");
            Integer fk_id_custodio = null; // Asumimos que este puede ser opcional o parte de tu formulario
            try {
                fk_id_custodio = Integer.parseInt(request.getParameter("fk_id_custodio"));
            } catch (NumberFormatException e) {
                // Manejo en caso de que fk_id_custodio sea nulo o no esté presente
            }

            Radio radio = new Radio(id, tipo, modelo, marca, serie, fk_id_custodio);

            radioDao.updateRadio(radio);

            response.sendRedirect("crudRadio.jsp");
        } catch (Exception e) {
            throw new ServletException("Error al actualizar radio", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "EditRadioServlet handles updating radio information";
    }
}
