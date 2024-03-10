<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="modelos.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Tecnico"%>
<%@page import="dao.TecnicoDAO"%>
<%@page import="modelos.Radio"%>
<%@page import="dao.RadioDAO"%>
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
        <style>
            body {
                background: linear-gradient(to right, #00c6ff 0%, #ffffff 100%);
                /* Un gradiente que comienza con un azul intenso (#00c6ff) y termina en blanco (#ffffff) */
            }
            /* Mantén el resto de tus estilos aquí */
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
                <!-- Page Content -->
                <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                    <div class="container mt-5">
                        <h2>Añadir Registro de Mantenimiento</h2>
                        <form action="AddMantenimientoServlet" method="POST">
                            <div class="form-group">
                                <label for="Tipo">Tipo</label>
                                <select class="form-control" id="Tipo" name="Tipo" required>
                                    <option value="Preventivo">Preventivo</option>
                                    <option value="Correctivo">Correctivo</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="Descripción">Estado Actual del Equipo</label>
                                <textarea class="form-control" id="Descripción" name="Descripción" rows="3" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="Fecha_recepción">Fecha de Recepción</label>
                                <input type="date" class="form-control" id="Fecha_recepción" name="Fecha_recepción" required>
                            </div>
                            <input type="hidden" id="fk_id_usuario" name="fk_id_usuario" value="<%= nombreUsuarioLogueado %>" required>
                            <div class="form-group">
                                <label for="Observación">Observación</label>
                                <textarea class="form-control" id="Observación" name="Observación" rows="3" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="fk_id_radio">Radio (Serie)</label>
                                <select class="form-control" id="fk_id_radio" name="fk_id_radio" required>
                                    <% 
                                        String jdbcURL = "jdbc:mysql://localhost:3306/met";
                                        String jdbcUsername = "root";
                                        String jdbcPassword = "";
                                    List<Radio> radios = new RadioDAO(jdbcURL, jdbcUsername, jdbcPassword).selectAllRadios();
                                    for (Radio radio : radios) {
                                    %>
                                    <option value="<%= radio.getPk_id_radio() %>"><%= radio.getSerie() %></option>
                                    <% 
                                    }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="fk_id_tecnico">Técnico</label>
                                <select class="form-control" id="fk_id_tecnico" name="fk_id_tecnico" required>
                                    <% 
                                    TecnicoDAO tecnicoDao = new TecnicoDAO();
                                    List<Tecnico> tecnicos = tecnicoDao.selectAllTecnicos();
                                    for (Tecnico tecnico : tecnicos) {
                                    %>
                                    <option value="<%= tecnico.getId() %>"><%= tecnico.getNombres() %></option>
                                    <% 
                                    }
                                    %>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Añadir Mantenimiento</button>
                        </form>
                    </div>
                    <!-- El resto de tu contenido aquí -->
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
