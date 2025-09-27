<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import= "entidades.Cuentas" %>
<%@ page import= "entidades.Usuarios" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cuentas</title>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
    <script type="text/javascript">
        $(document).ready(function() {
            $('#cuentas_id').DataTable();
        });    
     </script>    
     
     <script type="text/javascript">
        function confirmarEliminacion(form) {
            if (confirm("¿Está seguro que desea eliminar esta cuenta?")) {
                form.submit(); 
            }
        }
    </script>
    <style type="text/css">
		<jsp:include page="Css/Eliminar.css"></jsp:include>
	</style>
    
</head>
<body>
	<div class="encabezado">
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
		
		<div class="active" style="float:right;">
		    <%= mensaje2 %>
		</div>
	</div>

<div class="contenido">
    <address class="parteIzq">
            <br><br><br><br><br><br><br><br>
            <input class="btn" type="button" value="Agregar Cuenta" onclick="window.location.href='servletAgregarCuentas?Param=1'"><br><br><br>
            <input class="btn" type="button" value="Listar Cuentas" onclick="window.location.href='ServletListarCuenta?Param=1'"><br><br><br>
            <input class="btn" type="button" value="Eliminar Cuentas" onclick="window.location.href='servletEliminarCuenta?Param=1'"><br><br><br>
        </address>

<div class="parteDer">
		<h2>Eliminar Cuenta</h2>
		<%
			ArrayList<Cuentas> listaCuentas = null;
			if(request.getAttribute("listaU") != null){
				listaCuentas = (ArrayList<Cuentas>) request.getAttribute("listaU");
			}
		%>
		
		<table id="cuentas_id" class="display">
		    <thead>
		        <tr>
		        	<th>Numero de Cuenta</th>
		            <th>DNI Cliente</th>
		            <th>Tipo de Cuenta</th>
		            <th>Fecha de Creacion</th>
		            <th>CBU</th>
		            <th>Saldo</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<% if (listaCuentas != null) { 
		    		for (Cuentas cuenta : listaCuentas) { %>
		    			<tr>
		    				
		    					<td><%= cuenta.getNroCuenta()%>
		    					<td><%= cuenta.getDni() %></td>
		    					<td><%= cuenta.getTipoDeCuenta().getNombreCuenta() %></td>
		    					<td><%= cuenta.getFechaCreacion() %></td>
		    					<td><%= cuenta.getCbu() %></td>
		    					<td><%= cuenta.getSaldo() %></td>
		    					<td>
		    					<form action="servletEliminarCuenta" method="post" onsubmit="return confirmarEliminacion(this);">
		    						<input type="hidden" name="nroCuenta" value="<%= cuenta.getNroCuenta() %>">
		    						<input class="btnEliminar" type="submit" name="btnEliminar" value="Eliminar">
		    					</form>
		    					</td>
		    			</tr>
		    		<% }	
		    	} %>
		    </tbody>
		</table>
		<br><br>
	</div>
</div>

	<footer>
		<input class="btn-volver" type="button" value="Volver al Menú Principal" onclick="window.location.href='MenuAdministrador.jsp'">
	    <p><a href="Login.jsp" class="cerrar-sesion">Cerrar sesión</a></p>
	</footer>

</body>
</html>