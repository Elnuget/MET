<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Tecnico"%>
<%@page import="dao.TecnicoDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CRUD Técnico</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="home.jsp">
                <img src="https://img.icons8.com/ios-filled/50/000000/home.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Inicio
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">Cerrar Sesión</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- Verifica si el usuario está logueado -->
        <%
            if (session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect("index.html");
                return;
            }
        %>

        <div class="container mt-5">
            <h2>Gestión de Técnicos</h2>
            <div class="my-4">
                <a href="add-edit-tecnico.jsp" class="btn btn-primary">Añadir Técnico</a>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombres</th>
                        <th>Cédula</th>
                        <th>Celular</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        TecnicoDAO tecnicoDao = new TecnicoDAO();
                        List<Tecnico> listaTecnicos = tecnicoDao.selectAllTecnicos();
                        for(Tecnico tecnico : listaTecnicos) {
                    %>
                    <tr>
                        <td><%= tecnico.getId() %></td>
                        <td><%= tecnico.getNombres() %></td>
                        <td><%= tecnico.getCedula() %></td>
                        <td><%= tecnico.getCelular() %></td>
                        <td>
                            <a href="EditTecnicoServlet?id=<%= tecnico.getId() %>" class="btn btn-primary btn-sm">Editar</a>
                            <a href="DeleteTecnicoServlet?id=<%= tecnico.getId() %>" onclick="return confirm('¿Está seguro que desea eliminar este técnico?');" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS y dependencias -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
