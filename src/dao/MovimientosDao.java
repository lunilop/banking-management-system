package dao;
import entidades.Movimientos;
import java.util.List;



public interface MovimientosDao {
	public List<Movimientos> obtenerTodos(String dni);
}
