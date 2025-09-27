package negocioImp;
import dao.PrestamosDao;
import daoImp.PrestamosDaoImp;
import negocio.PrestamosNeg;
import entidades.Prestamos;
import java.util.ArrayList;

public class PrestamosNegImp implements PrestamosNeg{
	
	private PrestamosDao pDao = new PrestamosDaoImp();
	
	public PrestamosNegImp() {
		
	}
	
	public ArrayList<Prestamos> listarPrestamos() {
		return (ArrayList<Prestamos>) pDao.obtenerTodos();
	}
	
	public boolean actualizarEstadoNeg(Prestamos prestamo) {
	    return pDao.actualizarEstado(prestamo);
	}
}
