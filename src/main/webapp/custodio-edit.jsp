<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Custodio"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar Custodio</title>
    <!-- Bootstrap CSS para estilos -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Editar Custodio</h2>
        <%
            Custodio custodio = (Custodio) request.getAttribute("custodio");
        %>
        <form action="AddEditCustodioServlet" method="POST">
            <input type="hidden" name="id" value="<%= custodio.getId() %>">
            
            <div class="form-group">
                <label for="nombres">Nombres:</label>
                <input type="text" class="form-control" id="nombres" name="nombres" value="<%= custodio.getNombres() %>" required>
            </div>
            
            <div class="form-group">
                <label for="cedula">Cédula:</label>
                <input type="text" class="form-control" id="cedula" name="cedula" value="<%= custodio.getCedula() %>" required>
            </div>
            
            <div class="form-group">
                <label for="celular">Celular:</label>
                <input type="text" class="form-control" id="celular" name="celular" value="<%= custodio.getCelular() %>" required>
            </div>
            
            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="<%= custodio.getDireccion() %>" required>
            </div>
            
            <div class="form-group">
                <label for="correo">Correo:</label>
                <input type="email" class="form-control" id="correo" name="correo" value="<%= custodio.getCorreo() %>" required>
            </div>
            
            <div class="form-group">
                <label for="subzona">Subzona:</label>
                <input type="text" class="form-control" id="subzona" name="subzona" value="<%= custodio.getSubzona() %>">
            </div>
            
            <div class="form-group">
                <label for="distrito">Distrito:</label>
                <input type="text" class="form-control" id="distrito" name="distrito" value="<%= custodio.getDistrito() %>">
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
