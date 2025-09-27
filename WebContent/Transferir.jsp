<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuarios"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Css/Global.css">
<link rel="stylesheet" type="text/css" href="Css/SubMenu.css">
<link rel="stylesheet" type="text/css" href="Css/Agregar_Pedir.css">
<title>Insert title here</title>
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
	        <h2>Transferir</h2>
	        <br>
	        <form action="">		
	        	<div class="form-group">
		            <label class="label-agregar">Cuenta:</label>
		            <select><option> Seleccione una cuenta </option></select>
	        	</div> 
	        	<div class="form-group">
		        	<label class="label-agregar">CBU:</label>
		        	<input type="text">
		        </div>   
		        <div class="form-group">
		        	<label class="label-agregar">Cantidad de Dinero</label>
		        	<input type="numb">
		        </div>   <br>
		        <div class="button-container">
	        		<button class="BtnAceptar" value="btnTransferir" type="submit">Transferir</button>
    			</div><br>
		    </form>
	    </div>
	</div>

	<footer>
		<input class="btn-volver" type="button" value="Volver al Menú de Clientes" onclick="window.location.href='MenuCliente.jsp'">
	    <p><a href="Login.jsp" class="cerrar-sesion">Cerrar sesión</a></p>
	</footer>
</body>
</html>