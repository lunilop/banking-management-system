package negocio;
import java.util.ArrayList;
import entidades.Prestamos;

public interface PrestamosNeg {
	public ArrayList<Prestamos> listarPrestamos();
	public boolean actualizarEstadoNeg(Prestamos prestamo);
}
