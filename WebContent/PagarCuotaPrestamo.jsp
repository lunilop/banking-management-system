<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Usuarios"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Prestamo</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="Css/Global.css">
	<link rel="stylesheet" type="text/css" href="Css/SubMenu.css">
	<link rel="stylesheet" type="text/css" href="Css/Agregar_Pedir.css">
	<style type="text/css">
		<jsp:include page="Css/Prestamo.css"></jsp:include>
	</style>
	
    <script type="text/javascript">
        $(document).ready(function() {
            $('#cuotas_id').DataTable({
                "searching": false // Desactiva la búsqueda
            });
        });    
    </script>
</head>
<body>

		<%
            // Obtenemos el usuario desde la sesión
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            String mensaje2;

            // Verificamos si el usuario está en la sesión
            if (usuario != null) {
                mensaje2 = "Bienvenido/a " + usuario.getNombreUsuario();
            } else {
                mensaje2 = "Por favor, inicie sesión.";
            }
        %>
        <div class="mensaje-contenedor">
            <div class="active">
                <%= mensaje2 %>
            </div>
        </div>

	<br>

	<div class="contenido">
	    <address class="parteIzq">
	    	<br><br><br>
	    	<input class="btn" type="button" value="Transferir" onclick="window.location.href='servletTransferir?Param=1'"><br><br>
		    <input class="btn" type="button" value="PagoPrestamos" onclick="window.location.href='servletPagoPrestamos?Param=1'"><br><br>
		    <input class="btn" type="button" value="PedirPrestamos" onclick="window.location.href='servletPedirPrestamos?Param=1'"><br><br>
		    <input class="btn" type="button" value="InfoPersonal" onclick="window.location.href='servletInfoPersonal?Param=1'"><br><br>
	    </address>
	 <div class="parteDer"> 
		<h2>Pagos de Prestamos</h2>
		<br><br>
		<% 
		    // Verifica si hay un mensaje y lo muestra
		    String mensaje = (String) request.getAttribute("mensaje");
		    if (mensaje != null) { 
		%>
		        <p style="color: green; font-weight: bold;"><%= mensaje %></p>
		<% 
		    } 
		%>
		
		<table id="cuotas_id" class="display">
		    <thead>
		        <tr>
		            <th>ID Cuota</th>
		            <th>ID Prestamo</th>
		            <th>Numero de Cuota</th>
		            <th>Fecha de vencimiento</th>
		            <th>Monto</th>	          
		        </tr>
		    </thead>
		    <tbody>      
		    </tbody>
		</table>
	    </div>
	</div>

	<footer>
		<input class="btn-volver" type="button" value="Volver al Menú de Clientes" onclick="window.location.href='MenuCliente.jsp'">
	    <p><a href="Login.jsp" class="cerrar-sesion">Cerrar sesión</a></p>
	</footer>
</body>
</html>