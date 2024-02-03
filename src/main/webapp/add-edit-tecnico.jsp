<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Tecnico"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Añadir/Editar Técnico</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2><%= request.getAttribute("tecnico") != null ? "Editar Técnico" : "Añadir Técnico" %></h2>
    <form action="AddEditTecnicoServlet" method="post">
        <% Tecnico tecnico = (Tecnico) request.getAttribute("tecnico"); %>
        <input type="hidden" name="id" value="<%= tecnico != null ? tecnico.getId() : "" %>">

        <div class="form-group">
            <label for="nombres">Nombres:</label>
            <input type="text" class="form-control" id="nombres" name="nombres" value="<%= tecnico != null ? tecnico.getNombres() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="cedula">Cédula:</label>
            <input type="text" class="form-control" id="cedula" name="cedula" value="<%= tecnico != null ? tecnico.getCedula() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="celular">Celular:</label>
            <input type="text" class="form-control" id="celular" name="celular" value="<%= tecnico != null ? tecnico.getCelular() : "" %>" required>
        </div>

        <button type="submit" class="btn btn-primary"><%= tecnico != null ? "Actualizar" : "Guardar" %></button>
    </form>
</div>

<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
