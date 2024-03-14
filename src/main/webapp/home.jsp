<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
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
    background: #b3e5fc; /* Celeste claro */
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
                        <a href="home.jsp" class="list-group-item list-group-item-action active"><i class="fas fa-home"></i> Inicio</a>
                        <%-- Verificación del rol de usuario para mostrar el enlace Usuarios --%>
                        <% if ("admin".equals(rolUsuario)) { %>
                        <a href="crudUsuario.jsp" class="list-group-item list-group-item-action "><i class="fas fa-users"></i> Usuarios</a>

                        <a href="crudTecnico.jsp" class="list-group-item list-group-item-action"><i class="fas fa-tools"></i> Gestión de Técnicos</a>
                        <% } %>
                        <a href="crudCustodio.jsp" class="list-group-item list-group-item-action" ><i class="fas fa-shield-alt"></i> Gestión de Custodios</a>
                        <a href="crudRadio.jsp" class="list-group-item list-group-item-action"><i class="fas fa-broadcast-tower"></i> Gestión de Radios</a> 
                        <a href="crudMantenimiento.jsp" class="list-group-item list-group-item-action"><i class="fas fa-wrench"></i> Gestión de Mantenimientos</a>
                        <a href="LogoutServlet" class="list-group-item list-group-item-action text-danger"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
                        <div class="imagen-fondo">
                            <img src="img/fondo.jpeg" alt="Imagen de fondo" style="width:100%; height:100%; object-fit: cover;">
                        </div>
                    </div>
                </div>
                <!-- HEADER -->
                <!-- BODY -->
                <!-- Page Content -->
                <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                    <h1 class="mt-5">Bienvenido al Sistema de Equipos Terminales</h1>
                    <!-- El resto de tu contenido aquí -->
                </div>
            </div>
        </div>
        <!-- BODY -->
        <!-- FOOTER -->


        <!-- FOOTER -->
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
