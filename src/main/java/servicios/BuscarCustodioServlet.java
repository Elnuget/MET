package servicios;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import dao.CustodioDAO;
import modelos.Custodio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarCustodioServlet")
public class BuscarCustodioServlet extends HttpServlet {
    private CustodioDAO custodioDao;

    public void init() {
        custodioDao = new CustodioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        Custodio custodio = custodioDao.buscarPorCedula(cedula);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Respuesta respuesta = new Respuesta();
            if (custodio != null) {
                respuesta.setExiste(true);
                respuesta.setNombre(custodio.getNombres()); // Asume que Custodio tiene un método getNombres()
                respuesta.setId(custodio.getId()); // Asume que Custodio tiene un método getId()
            } else {
                respuesta.setExiste(false);
            }

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(respuesta);
            out.print(jsonResponse);
            out.flush();
        }
    }

    private class Respuesta {
        private boolean existe;
        private String nombre;
        private int id; // Agregamos esta propiedad para almacenar el ID del custodio

        // Getters y setters para todas las propiedades
        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
