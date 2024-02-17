<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Mantenimiento"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Mantenimiento</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .mantenimiento-details {
            margin-top: 20px;
        }
        .mantenimiento-details th {
            text-align: left;
            padding-right: 20px;
        }
        .mantenimiento-details td {
            padding-top: 5px;
        }
        @media print {
            .no-print {
                display: none;
            }
        }
    </style>
</head>
<body>
    <div class="mantenimiento-container">
        <h2>Detalle de Mantenimiento</h2>
        <table class="mantenimiento-details">
            <tr>
                <th>ID:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getPk_id_mantenimiento() %></td>
            </tr>
            <tr>
                <th>Tipo:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getTipo() %></td>
            </tr>
            <tr>
                <th>Descripción:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getDescripcion() %></td>
            </tr>
            <tr>
                <th>Fecha Recepción:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getFecha_recepcion() %></td>
            </tr>
            <tr>
                <th>Observación:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getObservacion() %></td>
            </tr>
            <tr>
                <th>Radio ID:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getFk_id_radio() != null ? ((Mantenimiento)request.getAttribute("mantenimiento")).getFk_id_radio().toString() : "N/A" %></td>
            </tr>
            <tr>
                <th>Técnico ID:</th>
                <td><%= ((Mantenimiento)request.getAttribute("mantenimiento")).getFk_id_tecnico() != null ? ((Mantenimiento)request.getAttribute("mantenimiento")).getFk_id_tecnico().toString() : "N/A" %></td>
            </tr>
        </table>
        <div class="no-print">
            <button onclick="window.print()">Imprimir</button>
            <button onclick="history.back()">Volver</button>
        </div>
    </div>
</body>
</html>
