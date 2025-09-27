package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuentas;
import entidades.Usuarios;
import negocio.CuentasNeg;
import negocioImp.CuentasNegImp;


@WebServlet("/servletEliminarCuenta")
public class servletEliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentasNeg cNeg = new CuentasNegImp();
       
    public servletEliminarCuenta() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); 
		Usuarios usuario = (Usuarios) session.getAttribute("usuario");
		
		if(request.getParameter("Param")!=null) {
			ArrayList<Cuentas> lista = cNeg.listarCuentas();
			request.setAttribute("listaU", lista);
			RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuenta.jsp");
			
			rd.forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnEliminar")!= null) {
			int nroCuenta = Integer.parseInt(request.getParameter("nroCuenta"));
			boolean eliminado = cNeg.borrarCuenta(nroCuenta);
			
			if(eliminado) {
				request.setAttribute("mensaje", "La cuenta con Número de cuenta " + nroCuenta + " fue eliminada correctamente");
			}else {
				request.setAttribute("mensaje", "No se pudo eliminar la cuenta con número de cuenta " + nroCuenta);
			}
			
			ArrayList<Cuentas> lista = cNeg.listarCuentas();
			request.setAttribute("listaU", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("EliminarCuenta.jsp");
			rd.forward(request, response);
			
		}
		doGet(request, response);
	}

}
