<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.TipoCuentas" %>
<%@ page import= "entidades.Usuarios" %>
<%@ page import="entidades.Cuentas" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Cuentas</title>
<style type="text/css">
    <jsp:include page="Css/ListarCliente.css"></jsp:include>
</style>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script>
        function confirmarModificacion(event, filaId) {	   
		    try {
		        const confirmar = confirm("¿Estás seguro de que deseas modificar esta cuenta?");
		        if (!confirmar) {
		            event.preventDefault(); 
		        }
		    } catch (error) {
		        console.error("Ocurrió un error al intentar modificar la cuenta:", error);
		        alert("Hubo un problema al intentar modificar la cuenta. Por favor, inténtalo de nuevo.");
		        event.preventDefault(); 
		    }
		}  
</script>
	<script type="text/javascript">
    	$(document).ready(function() {
    		$('#cuentas_id').DataTable();
    	});
    </script>
    

</head>

<body>

	    <% 
		    ArrayList<Cuentas> listaCuentas = null;
		    if (request.getAttribute("listaU") != null) {
		    	listaCuentas = (ArrayList<Cuentas>) request.getAttribute("listaU");
		    }
		%>


<div class="encabezadoListarCuenta"><%
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
</div>

	<div class="contenido">
	    <address class="parteIzqi">
            <br><br><br><br><br><br><br><br>
            <input class="btnA" type="button" value="Agregar Cuenta" onclick="window.location.href='servletAgregarCuentas?Param=1'"><br><br><br>
            <input class="btnA" type="button" value="Listar Cuentas" onclick="window.location.href='ServletListarCuenta?Param=1'"><br><br><br>
            <input class="btnA" type="button" value="Eliminar Cuentas" onclick="window.location.href='servletEliminarCuenta?Param=1'"><br><br><br>
        </address>
	 <div class="parteDere"> 

	        <h2>Listar Cuentas</h2>
	        <br>
			<form action="ServletListarCuenta" method="get">
				<div class="info">
			       	  <div class="columna-filtros">
			       	  	<div class="fila">
						<label class="label-filtrar">Filtrar por Tipo Cuenta:</label>
		                    <select  class="custom-select"  name="ddltipoCuenta" >
		                        <option value="">--Seleccione Tipo de Cuenta--</option>
					 	       	<% ArrayList<TipoCuentas> cargarDDLTipoCuenta = (ArrayList<TipoCuentas>) request.getAttribute("cargarDDlTipoCuenta");
				                    if(cargarDDLTipoCuenta!= null){
				                        for(TipoCuentas Tipo : cargarDDLTipoCuenta){ %>
				                            <option value="<%= Tipo.getIdTipoCuenta() %>"><%= Tipo.getNombreCuenta() %></option>
				                <% } } %>
		                    </select><input class="btnFiltrarI" type="submit" name="btnFlitrarTipoCuenta" value=" Filtrar ">
			 		  </div>
           			 <div class="fila">
	 				<label class="label-agregar">Buscar por DNI:</label>
					<input type="text" name="txtBuscarPorDniCliente">
					<input class="btnFiltrarI" type="submit" name="btnBuscarPorDni" value="Buscar">
					 </div>
       			 </div>
	             <div class="columna-mostrar"><br><br><br><br><br><br><br>
	             <input class="btnMostrar" type="submit" name="btnMostrarTodo" value="Mostrar Todo">
      		   </div>   
      		   </div> 
            </form><br>         <br><br> 
			
			<table id="cuentas_id" class="blueTable">
				<thead>
					<tr>
						<th>NroCuenta</th>
						<th>DNI del Cliente</th>
						<th>Fecha de Creación</th>
						<th>Tipo de Cuenta</th>
						<th>CBU</th>
						<th>Saldo</th>		
					</tr>
				</thead>
				<tbody>
				<% if (listaCuentas != null) {
                    int filaId = 0;
                    for (Cuentas cuenta : listaCuentas) { %>
                    <tr>
	       			<form action="ServletListarCuenta?idFila=<%= filaId %>" method="post">
							<td><input type="text" name="txtNroCuenta"  value="<%= cuenta.getNroCuenta() %>" ></td>
							<td><input type="text" name="txtDniCliente" style="width: 95px;"  value="<%= cuenta.getDni() %>" ></td>
							<td><input type="date" name="txtFecha" value="<%= cuenta.getFechaCreacion() %>" ></td>
							<td>
                                <select style="width: 150px;" name="ddlTipoCuenta" id="ddlTipoCuenta_<%= filaId %>">
                                   <option selected value="<%=cuenta.getTipoDeCuenta().getIdTipoCuenta() %>"><%= cuenta.getTipoDeCuenta().getNombreCuenta() %></option>
                                    <%
                                        ArrayList<TipoCuentas> ddlTipoCuentas = (ArrayList<TipoCuentas>) request.getAttribute("cargarDDlTipoCuenta");
                                        if (ddlTipoCuentas != null) {
                                            for (TipoCuentas Tipo : ddlTipoCuentas) {
                                    %>
                                                <option value="<%= Tipo.getIdTipoCuenta() %>"><%= Tipo.getNombreCuenta() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </td>
							<td><input type="text" name="txtCBU" style="width: 170px;" value="<%= cuenta.getCbu() %>" ></td>
							<td><input type="number" name="txtSaldo" style="width: 110px;" value="<%= cuenta.getSaldo() %>" ></td>
							<td><input class="BtnModificar" type="submit" name="btnModificar" value="Modificar"></td>
					</form>	
					</tr>
					<% filaId++; } } %>
				</tbody>
			</table><br><br>
	    </div>
	</div>

	<footer class="listarCuenta">
		<input class="btn-volver" type="button" value="Volver al Menú Principal" onclick="window.location.href='MenuAdministrador.jsp'">
        <p><a href="Login.jsp" class="cerrar-sesion">Cerrar sesión</a></p>
	</footer>
</body>
</html>