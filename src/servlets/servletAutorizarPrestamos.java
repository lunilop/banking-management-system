package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Usuarios;
import javax.servlet.http.HttpSession;

import entidades.Clientes;
import entidades.Prestamos;
import negocio.PrestamosNeg;
import negocioImp.PrestamosNegImp;

/**
 * Servlet implementation class servletAutorizarPrestamos
 */
@WebServlet("/servletAutorizarPrestamos")
public class servletAutorizarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrestamosNeg pNeg = new PrestamosNegImp();

    public servletAutorizarPrestamos() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Recuperar la sesión actual sin crear una nueva
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        if (request.getParameter("Param") != null) {
            // Listar los préstamos y enviarlos al JSP
            ArrayList<Prestamos> lista = pNeg.listarPrestamos();
            request.setAttribute("listaU", lista);

            RequestDispatcher rd = request.getRequestDispatcher("AutorizacionPrestamos.jsp");
            rd.forward(request, response);
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnModificar") != null) {
	        // Obtener los parámetros necesarios
	        int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
	        String nuevoEstado = request.getParameter("ddlEstado");

	        // Actualizar el estado del préstamo
	        Prestamos prestamo = new Prestamos();
	        prestamo.setIdPrestamo(idPrestamo);
	        prestamo.setEstado(nuevoEstado);

	        boolean actualizado = pNeg.actualizarEstadoNeg(prestamo);

	        // Verificar el resultado de la operación
	        if (actualizado) {
	            request.setAttribute("mensaje", "Estado actualizado correctamente.");
	        } else {
	            request.setAttribute("mensaje", "Error al actualizar el estado.");
	        }

	        // Volver a cargar la lista de préstamos para mostrar en el JSP
	        ArrayList<Prestamos> lista = pNeg.listarPrestamos();
	        request.setAttribute("listaU", lista);

	        RequestDispatcher rd = request.getRequestDispatcher("AutorizacionPrestamos.jsp");
	        rd.forward(request, response);
	        return;
	    }
		doGet(request, response);
	}

}
