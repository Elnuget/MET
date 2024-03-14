<%-- 
    Document   : login
    Created on : 30 ene 2024, 15:42:18
    Author     : cangu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Mantenimiento de equipos terminales</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Incluir Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Incluir Bootstrap Optional JavaScript y dependencias -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <%
            // Verificar si el usuario ya está logueado
            if (session != null && session.getAttribute("usuarioLogueado") != null) {
                // Si el usuario ya está logueado, redirigir a home.jsp
                response.sendRedirect("home.jsp");
                return;
            }
        %>
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

       <style>
            
            /* Mantén el resto de tus estilos aquí */
        </style>

        <div class="imagen-fondo">
            <img src="img/fondo.jpeg" alt="Imagen de fondo" style="width:100%; height:100%; object-fit: cover;">
        </div>

        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="text-center">Iniciar Sesión</h3>
                        </div>
                        <div class="card-body">
                            <form action="LoginServlet" method="POST">
                                <div class="form-group">
                                    <label for="username">Usuario:</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Contraseña:</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Ingresar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
