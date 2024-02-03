<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Custodio"%>
<%@page import="dao.CustodioDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Custodios</title>
        <!-- Bootstrap CSS para estilos -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
        <div class="container mt-4">
            <!-- Verifica si el usuario está logueado -->
            <%
                if (session == null || session.getAttribute("usuarioLogueado") == null) {
                    response.sendRedirect("index.html");
                    return;
                }
            %>

            <% if (request.getAttribute("mensaje") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("mensaje") %>
            </div>
            <% } %>
            <h1 class="mb-4">Gestión de Custodios</h1>

            <!-- Formulario para añadir/editar un custodio en una tarjeta -->
            <div class="card">
                <div class="card-header">Añadir/Editar Custodio</div>
                <div class="card-body">
                    <form action="AddEditCustodioServlet" method="POST">
                        <!-- Este input escondido es para el ID del custodio, que es necesario para editar -->
                        <input type="hidden" name="id" id="id" value="">

                        <div class="form-group">
                            <label for="nombres">Nombres:</label>
                            <input type="text" name="nombres" id="nombres" required class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="cedula">Cédula:</label>
                            <input type="text" name="cedula" id="cedula" required class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="celular">Celular:</label>
                            <input type="text" name="celular" id="celular" required class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="direccion">Dirección:</label>
                            <input type="text" name="direccion" id="direccion" required class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="correo">Correo:</label>
                            <input type="email" name="correo" id="correo" required class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="subzona">Subzona:</label>
                            <input type="text" name="subzona" id="subzona" class="form-control form-control-sm">
                        </div>

                        <div class="form-group">
                            <label for="distrito">Distrito:</label>
                            <input type="text" name="distrito" id="distrito" class="form-control form-control-sm">
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div>


            <hr>

            <!-- Lista de custodios existentes -->
            <h2>Listado de Custodios</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombres</th>
                        <th>Cédula</th>
                        <th>Celular</th>
                        <th>Dirección</th>
                        <th>Correo</th>
                        <th>Subzona</th>
                        <th>Distrito</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        CustodioDAO custodioDao = new CustodioDAO();
                        List<Custodio> listaCustodios = custodioDao.selectAllCustodios();
                        for (Custodio custodio : listaCustodios) {
                    %>
                    <tr>
                        <td><%= custodio.getId() %></td>
                        <td><%= custodio.getNombres() %></td>
                        <td><%= custodio.getCedula() %></td>
                        <td><%= custodio.getCelular() %></td>
                        <td><%= custodio.getDireccion() %></td>
                        <td><%= custodio.getCorreo() %></td>
                        <td><%= custodio.getSubzona() %></td>
                        <td><%= custodio.getDistrito() %></td>
                        <td>
                            <a href="EditCustodioServlet?id=<%= custodio.getId() %>" class="btn btn-primary btn-sm">Editar</a>
                            <a href="DeleteCustodioServlet?id=<%= custodio.getId() %>" onclick="return confirm('¿Estás seguro de querer eliminar este custodio?');" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <!-- Bootstrap JS y dependencias -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
