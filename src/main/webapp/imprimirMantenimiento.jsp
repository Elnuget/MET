<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Mantenimiento"%>
<%@page import="dao.MantenimientoDAO"%>
<%@page import="modelos.Radio"%>
<%@page import="dao.RadioDAO"%>
<%@page import="modelos.Tecnico"%>
<%@page import="dao.TecnicoDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio del Sistema de Mantenimiento de Equipos Terminales</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            .mantenimiento-details {
                margin-top: 20px;
                width: 100%; /* Asegura que la tabla ocupe todo el ancho disponible */
                border-collapse: collapse; /* Elimina el espacio entre los bordes de las celdas */
            }
            .mantenimiento-details th, .mantenimiento-details td {
                border: 1px solid black; /* Agrega un borde sólido negro a las celdas */
                text-align: center; /* Centra el texto en las celdas */
                padding: 8px; /* Agrega un poco de padding para que el contenido no esté pegado al borde */
            }
            .mantenimiento-details th {
                background-color: #f2f2f2; /* Agrega un color de fondo a los encabezados para diferenciarlos */
                text-align: left; /* Mantiene el texto de los encabezados alineado a la izquierda */
            }
            @media print {
                .no-print {
                    display: none;
                }
            }
        </style>
        <style>
            body {
                background: linear-gradient(to right, #00c6ff 0%, #ffffff 100%);
                /* Un gradiente que comienza con un azul intenso (#00c6ff) y termina en blanco (#ffffff) */
            }
            /* Mantén el resto de tus estilos aquí */
        </style>

    </head>
    <body>
        <!-- HEADER -->
        <%
    String nombreUsuarioLogueado = (String) session.getAttribute("usuarioLogueado");
String rolUsuario = (String) session.getAttribute("rolUsuario");
if (nombreUsuarioLogueado == null || rolUsuario == null) {
    response.sendRedirect("index.html");
    return;
}

        %>
        <style>
            /* Estilo para la sidebar */
            .sidebar {
                min-height: 100vh;
            }
        </style>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse custom-margin-top">
                    <div class="list-group">
                        <!-- Icono y nombre del usuario -->
                        <a href="#" class="list-group-item list-group-item-action disabled" aria-disabled="true">
                            <div class="text-center">
                                <i class="fas fa-user-circle fa-2x"></i> <!-- Icono del usuario -->
                                <div><strong><%= nombreUsuarioLogueado %></strong></div> <!-- Nombre del usuario -->
                                <div><%= rolUsuario %></div> <!-- Rol del usuario -->
                            </div>
                        </a>
                        <a href="home.jsp" class="list-group-item list-group-item-action "><i class="fas fa-home"></i> Inicio</a>
                        <%-- Verificación del rol de usuario para mostrar el enlace Usuarios --%>
                        <% if ("admin".equals(rolUsuario)) { %>
                        <a href="crudUsuario.jsp" class="list-group-item list-group-item-action  "><i class="fas fa-users"></i> Usuarios</a>

                        <a href="crudTecnico.jsp" class="list-group-item list-group-item-action"><i class="fas fa-tools"></i> Gestión de Técnicos</a>
                        <% } %>
                        <a href="crudCustodio.jsp" class="list-group-item list-group-item-action" ><i class="fas fa-shield-alt"></i> Gestión de Custodios</a>
                        <a href="crudRadio.jsp" class="list-group-item list-group-item-action"><i class="fas fa-broadcast-tower"></i> Gestión de Radios</a> 
                        <a href="crudMantenimiento.jsp" class="list-group-item list-group-item-action active"><i class="fas fa-wrench"></i> Gestión de Mantenimientos</a>
                        <a href="LogoutServlet" class="list-group-item list-group-item-action text-danger"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>

                    </div>
                </div>
                <!-- HEADER -->
                <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                    <div class="mantenimiento-container">
                        <h2>Detalle de Mantenimiento</h2>
                        <table class="mantenimiento-details">
                            <tr>
                                <th>ID:</th>
                                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getPk_id_mantenimiento() %></td>
                            </tr>
                            <tr>
                                <th>Tipo:</th>
                                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getTipo() %></td>
                            </tr>
                            <tr>
                                <th>Descripción:</th>
                                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getDescripcion() %></td>
                            </tr>
                            <tr>
                                <th>Fecha Recepción:</th>
                                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getFecha_recepcion() %></td>
                            </tr>
                            <tr>
                                <th>Observación:</th>
                                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getObservacion() %></td>
                            </tr>
                            <%
            // Suponiendo que ya tienes acceso a las instancias de RadioDAO y TecnicoDAO aquí
            // Puedes adaptar este código para obtenerlos según cómo los manejes en tu aplicación

            // Obtener el mantenimiento desde el request
            String jdbcURL = "jdbc:mysql://localhost:3306/met";
                                String jdbcUsername = "root";
                                String jdbcPassword = "";
            Mantenimiento mantenimiento = (Mantenimiento) request.getAttribute("mantenimiento");
MantenimientoDAO mantenimientoDao = new MantenimientoDAO(jdbcURL, jdbcUsername, jdbcPassword);
RadioDAO radioDao = new RadioDAO(jdbcURL, jdbcUsername, jdbcPassword);
TecnicoDAO tecnicoDao = new TecnicoDAO();

            // Inicializar variables para la serie de la radio y el nombre del técnico
            String serieRadio = "N/A";
            String nombreTecnico = "N/A";

            // Verificar si el mantenimiento tiene asignado un radio y un técnico
            if (mantenimiento.getFk_id_radio() != null) {
                Radio radio = radioDao.findradioById(mantenimiento.getFk_id_radio());
                if (radio != null) {
                    serieRadio = radio.getSerie(); // Asumiendo que el método para obtener la serie se llama getSerie()
                }
            }

            if (mantenimiento.getFk_id_tecnico() != null) {
                Tecnico tecnico = tecnicoDao.findtecnicoById(mantenimiento.getFk_id_tecnico());
                if (tecnico != null) {
                    nombreTecnico = tecnico.getNombres(); // Asumiendo que el método para obtener el nombre se llama getNombres()
                }
            }
                            %>

                            <tr>
                                <th>Serie de Radio:</th>
                                <td><%= serieRadio %></td>
                            </tr>
                            <tr>
                                <th>Nombre del Técnico:</th>
                                <td><%= nombreTecnico %></td>
                            </tr>

                        </table>
                       <div class="no-print">
    <button onclick="window.print()" class="btn btn-primary">Imprimir</button>
    <button onclick="history.back()" class="btn btn-secondary">Volver</button>
</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- BODY -->
        <!-- FOOTER -->
        <style>
            .imagen-fondo {
                position: fixed; /* Posicionamiento fijo respecto a la ventana del navegador */
                bottom: 0; /* Alineado abajo */
                right:  0; /* Alineado a la izquierda */
                z-index: -1; /* Coloca el div detrás de todo el contenido */
            }

            .imagen-fondo img {
                opacity: 0.5; /* Ajusta la transparencia de la imagen (0 completamente transparente, 1 completamente opaco) */
                width: 300px; /* O el tamaño que prefieras */
                height: auto; /* Para mantener la proporción de la imagen */
            }
        </style>
        <div class="imagen-fondo">
            <img src="img/fondo.jpeg" alt="Imagen de fondo" style="width:100%; height:100%; object-fit: cover;">
        </div>
        <!-- FOOTER -->
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
