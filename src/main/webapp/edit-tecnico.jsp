<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Tecnico"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Técnico</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Editar Técnico</h2>
    <form action="AddEditTecnicoServlet" method="post">
        <input type="hidden" name="id" value="${tecnico.id}">

        <div class="form-group">
            <label for="nombres">Nombres:</label>
            <input type="text" class="form-control" id="nombres" name="nombres" value="${tecnico.nombres}" required>
        </div>

        <div class="form-group">
            <label for="cedula">Cédula:</label>
            <input type="text" class="form-control" id="cedula" name="cedula" value="${tecnico.cedula}" required>
        </div>

        <div class="form-group">
            <label for="celular">Celular:</label>
            <input type="text" class="form-control" id="celular" name="celular" value="${tecnico.celular}" required>
        </div>

        <button type="submit" class="btn btn-primary">Actualizar</button>
    </form>
</div>

<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
