package dao;
import java.util.List;
import entidades.Prestamos;

public interface PrestamosDao {
	public List<Prestamos> obtenerTodos();
	public boolean actualizarEstado(Prestamos prestamo);
}
