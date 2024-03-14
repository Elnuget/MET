<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="modelos.Custodio"%>
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
        <!-- Incluye Select2 CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />

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
                        <a href="home.jsp" class="list-group-item list-group-item-action "><i class="fas fa-home"></i> Inicio</a>
                        <%-- Verificación del rol de usuario para mostrar el enlace Usuarios --%>
                        <% if ("admin".equals(rolUsuario)) { %>
                        <a href="crudUsuario.jsp" class="list-group-item list-group-item-action "><i class="fas fa-users"></i> Usuarios</a>

                        <a href="crudTecnico.jsp" class="list-group-item list-group-item-action"><i class="fas fa-tools"></i> Gestión de Técnicos</a>
                        <% } %>
                        <a href="crudCustodio.jsp" class="list-group-item list-group-item-action active" ><i class="fas fa-shield-alt"></i> Gestión de Custodios</a>
                        <a href="crudRadio.jsp" class="list-group-item list-group-item-action"><i class="fas fa-broadcast-tower"></i> Gestión de Radios</a> 
                        <a href="crudMantenimiento.jsp" class="list-group-item list-group-item-action"><i class="fas fa-wrench"></i> Gestión de Mantenimientos</a>
                        <a href="LogoutServlet" class="list-group-item list-group-item-action text-danger"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>

                    </div>
                </div>
                <!-- HEADER -->
                <!-- BODY -->
                <!-- Page Content -->
                <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                    <div class="container mt-5">
                        <h2>Editar Custodio</h2>
    <% Custodio custodio = (Custodio) request.getAttribute("custodio"); %>
    <form action="EditCustodioServlet" method="post">
        <input type="hidden" name="id" value="<%= custodio.getId() %>">
        
        <div class="form-group">
            <label for="Rango">Grado/Rango</label>
            <select class="form-control" id="Rango" name="Rango">
                <option <%= "P.N.".equals(custodio.getRango()) ? "selected" : "" %>>P.N.</option>
                <!-- Repite la opción anterior para cada rango, ajustando la condición de selección -->
            </select>
        </div>
        
        <div class="form-group">
            <label for="Nombres">Nombres</label>
            <input type="text" class="form-control" id="Nombres" name="Nombres" value="<%= custodio.getNombres() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Cedula">Cédula</label>
            <input type="text" class="form-control" id="Cedula" name="Cedula" value="<%= custodio.getCedula() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Celular">Celular</label>
            <input type="text" class="form-control" id="Celular" name="Celular" value="<%= custodio.getCelular() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Direccion">Dirección</label>
            <input type="text" class="form-control" id="Direccion" name="Direccion" value="<%= custodio.getDireccion() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Correo">Correo Electrónico</label>
            <input type="email" class="form-control" id="Correo" name="Correo" value="<%= custodio.getCorreo() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Subzona">Subzona</label>
            <input type="text" class="form-control" id="Subzona" name="Subzona" value="<%= custodio.getSubzona() %>" required>
        </div>
        
        <div class="form-group">
            <label for="Distrito">Distrito</label>
            <input type="text" class="form-control" id="Distrito" name="Distrito" value="<%= custodio.getDistrito() %>" required>
        </div>
        
        <button type="submit" class="btn btn-primary">Actualizar Custodio</button>
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
        <!-- Incluye jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Incluye Select2 JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#rango').select2({
                    placeholder: "Selecciona una opción",
                    allowClear: true
                });
            });
        </script>

    </body>
</html>
