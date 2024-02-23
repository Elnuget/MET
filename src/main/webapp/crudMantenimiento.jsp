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
        <title>MET|Mantenimiento</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

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
                        <a href="crudUsuario.jsp" class="list-group-item list-group-item-action "><i class="fas fa-users"></i> Usuarios</a>
                        
                        <a href="crudTecnico.jsp" class="list-group-item list-group-item-action"><i class="fas fa-tools"></i> Gestión de Técnicos</a>
                        <% } %>
                        <a href="crudCustodio.jsp" class="list-group-item list-group-item-action" ><i class="fas fa-shield-alt"></i> Gestión de Custodios</a>
                        <a href="crudRadio.jsp" class="list-group-item list-group-item-action"><i class="fas fa-broadcast-tower"></i> Gestión de Radios</a> 
                        <a href="crudMantenimiento.jsp" class="list-group-item list-group-item-action active"><i class="fas fa-wrench"></i> Gestión de Mantenimientos</a>

                        <a href="LogoutServlet" class="list-group-item list-group-item-action text-danger"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>

                    </div>
                </div>
                <!-- HEADER -->
                <!-- BODY -->
                <div class="col-md-9 col-lg-10">
                    <h2>Mantenimientos</h2>
                    <div class="my-4">
                        <a href="add-mantenimiento.jsp" class="btn btn-primary">Añadir Mantenimiento</a>
                    </div>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Tipo</th>
                                <th>Estado Actual del Equipo</th>
                                <th>Fecha Recepción</th>

                                <th>Observación</th>
                                <th>Serie</th>
                                <th>Técnico</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                String jdbcURL = "jdbc:mysql://localhost:3306/met";
                                String jdbcUsername = "root";
                                String jdbcPassword = "";
                                MantenimientoDAO mantenimientoDao = new MantenimientoDAO(jdbcURL, jdbcUsername, jdbcPassword);
                                RadioDAO radioDao = new RadioDAO(jdbcURL, jdbcUsername, jdbcPassword);
                                TecnicoDAO tecnicoDao = new TecnicoDAO();
                                List<Mantenimiento> listMantenimientos = mantenimientoDao.selectAllMantenimientos();
                                for(Mantenimiento mantenimiento : listMantenimientos) {
                                String nombreRadio = "Sin asignar"; //
                                String nombreTecnico = "Sin asignar"; //
                                 if (mantenimiento.getFk_id_radio() != null && mantenimiento.getFk_id_tecnico() != null) {
        Radio radio = radioDao.findradioById(mantenimiento.getFk_id_radio());
        Tecnico tecnico = tecnicoDao.findtecnicoById(mantenimiento.getFk_id_tecnico());
        if (radio != null && tecnico!= null) {
            nombreRadio = radio.getSerie(); // O el método adecuado para obtener el nombre del custodio
            nombreTecnico = tecnico.getNombres();
        }
    }
                            %>
                            <tr>
                                <td><%= mantenimiento.getPk_id_mantenimiento() %></td>
                                <td><%= mantenimiento.getTipo() %></td>
                                <td><%= mantenimiento.getDescripcion() %></td>
                                <td><%= mantenimiento.getFecha_recepcion() %></td>

                                <td><%= mantenimiento.getObservacion() %></td>
                                <td><%= nombreRadio %></td>
                                <td><%= nombreTecnico %></td>
                                <td>
                                    <a href="ImprimirMantenimientoServlet?id=<%= mantenimiento.getPk_id_mantenimiento() %>"  class="btn btn-primary btn-sm">Imprimir</a>
                                    <!-- Suponiendo que estás dentro de un bucle que recorre los mantenimientos -->
                                    <a href="VerificarEntregaServlet?fkIdMantenimiento=<%= mantenimiento.getPk_id_mantenimiento() %>" class="btn btn-success btn-sm">Entregar</a>


                                    <a href="DeleteMantenimientoServlet?id=<%= mantenimiento.getPk_id_mantenimiento() %>" onclick="return confirm('¿Está seguro que desea eliminar este mantenimiento?');" class="btn btn-danger btn-sm">Eliminar</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
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
