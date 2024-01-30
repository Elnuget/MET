<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio del Sistema de Mantenimiento de Radios</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Sistema de Mantenimiento de Radios</h1>
        
        <!-- Verifica si el usuario está logueado -->
        <%
            if (session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect("index.html");
                return;
            }
        %>

        <!-- Botones para dirigirse a los CRUDs de cada tabla -->
        <div class="row">
            <div class="col-md-4 mb-3">
                <a href="crudAsignacion.jsp" class="btn btn-primary btn-block">Gestión de Asignaciones</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudCustodio.jsp" class="btn btn-secondary btn-block">Gestión de Custodios</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudEntrega.jsp" class="btn btn-success btn-block">Gestión de Entregas</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudMantenimiento.jsp" class="btn btn-danger btn-block">Gestión de Mantenimientos</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudOrdenTrabajo.jsp" class="btn btn-warning btn-block">Gestión de Órdenes de Trabajo</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudRadio.jsp" class="btn btn-info btn-block">Gestión de Radios</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudReportes.jsp" class="btn btn-light btn-block">Gestión de Reportes</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="crudTecnico.jsp" class="btn btn-dark btn-block">Gestión de Técnicos</a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
