<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Asignaciones</title>
    <!-- Incluir Bootstrap CSS para el estilo -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Gestión de Asignaciones</h1>

        <!-- Formulario para añadir una nueva asignación -->
        <form action="AddAsignacionServlet" method="POST">
            <input type="datetime-local" name="fechaAsignacion" required>
            <input type="number" name="idTecnico" placeholder="ID del Técnico" required>
            <input type="number" name="idRadio" placeholder="ID del Radio" required>
            <button type="submit" class="btn btn-success">Añadir Asignación</button>
        </form>

        <!-- Lista de asignaciones existentes -->
        <table class="table">
            <thead>
                <tr>
                    <th>ID Asignación</th>
                    <th>Fecha Asignación</th>
                    <th>ID Técnico</th>
                    <th>ID Radio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%-- Aquí deberías iterar sobre las asignaciones existentes --%>
                <%-- Puedes obtener las asignaciones de la base de datos en tu servlet y pasarlas a JSP --%>
                <%-- Por ejemplo: for (Asignacion asignacion : asignaciones) { --%>
                <tr>
                    <td><%-- asignacion.id --%></td>
                    <td><%-- asignacion.fechaAsignacion --%></td>
                    <td><%-- asignacion.idTecnico --%></td>
                    <td><%-- asignacion.idRadio --%></td>
                    <td>
                        <a href="EditAsignacionServlet?id=<%-- asignacion.id --%>" class="btn btn-primary">Editar</a>
                        <a href="DeleteAsignacionServlet?id=<%-- asignacion.id --%>" class="btn btn-danger">Eliminar</a>
                    </td>
                </tr>
                <%-- } --%>
            </tbody>
        </table>

    </div>
    <!-- Bootstrap JS y dependencias -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
