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
import entidades.TipoCuentas;
import negocio.TipoCuentaNeg;
import negocioImp.TipoCuentaNegImp;
import entidades.Cuentas;
import negocioImp.ClienteNegImp;
import negocioImp.CuentasNegImp;
import negocioImp.UsuarioNegImp;

/**
 * Servlet implementation class serletAgregarClientes
 */
@WebServlet("/servletAgregarCuentas")
public class servletAgregarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioNegImp negUsuarios = new UsuarioNegImp();
	ClienteNegImp negClientes = new ClienteNegImp();
	CuentasNegImp negCuentas = new CuentasNegImp();
	TipoCuentaNeg tNeg= new TipoCuentaNegImp();

    public servletAgregarCuentas() {
        super();
       
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si el parámetro Param es 1, redirigir al login
        if ((request.getParameter("Param")!=null)) {
        	ArrayList<TipoCuentas> tiposCuentas = tNeg.cargarDDlTipoCuenta();
        	request.setAttribute("tiposCuentas", tiposCuentas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCuenta.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        if(request.getParameter("btnAgregar") != null) {
            String cbu = request.getParameter("txtCbu");
            String dni = request.getParameter("txtDni");
            String tipoCuenta = request.getParameter("ddlTipoCuenta");
            String fechaCreacion = request.getParameter("FechaCreacion");
	        String nroCuenta = request.getParameter("txtNroCuenta");
	        
	        boolean agregado = false;
	        boolean existeCliente = negClientes.validarCliente(dni);
	        
	        if(negCuentas.cantidadCuentas(dni)<3) {
	        	 agregado = CargarCuenta(cbu, dni, tipoCuenta, fechaCreacion, nroCuenta);
	        	 if(agregado) {
	        		 request.setAttribute("mensaje", "Cuenta agregada con exito.");
	        	 }else {
	        		 request.setAttribute("mensaje", "Error al agregar cuenta.");
	        	 }
	        }else {
	        	request.setAttribute("mensaje", "El cliente con DNI " + dni + " ya tiene 3 o mas cuentas.");
	        }
	        
	        
	        ArrayList<TipoCuentas> tiposCuentas = tNeg.cargarDDlTipoCuenta();
        	request.setAttribute("tiposCuentas", tiposCuentas);
	    }
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCuenta.jsp");
            dispatcher.forward(request, response);
          
            response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    
    
    public boolean CargarCuenta(String cbu, String dni, String tipoCuenta, String fechaCreacion, String nroCuenta) {
        Cuentas cuenta = new Cuentas();
        cuenta.setCbu(cbu);
        cuenta.setDni(dni);
       // le asigno el tipo de cuenta
        ArrayList<TipoCuentas> listaTipoCuentas = tNeg.cargarDDlTipoCuenta();
        for (TipoCuentas tipo : listaTipoCuentas) {
            if (tipo.getIdTipoCuenta() == Integer.parseInt(tipoCuenta)) {
                cuenta.setTipoDeCuenta(tipo); 
                break;
            }
        }
        
        LocalDate auxFechaCreacion = LocalDate.parse(fechaCreacion);
        cuenta.setLocalDate(auxFechaCreacion);
        
        int auxNroCuenta = Integer.parseInt(nroCuenta);
        cuenta.setNroCuenta(auxNroCuenta);
        
        cuenta.setSaldo(10000); 
        cuenta.setEstado(true); 
       
        return negCuentas.agregarCuentas(cuenta);
    }

    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
