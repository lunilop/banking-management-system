package negocioImp;
import java.util.ArrayList;

import dao.MovimientosDao;
import daoImp.MovimientosDaoImp;
import entidades.Movimientos;
import negocio.MovimientosNeg;

public class MovimientosNegImp implements MovimientosNeg{
	private MovimientosDao mDao = new MovimientosDaoImp();
	
	public ArrayList<Movimientos> listarMovimientos(String dni){
		return (ArrayList<Movimientos>) mDao.obtenerTodos(dni);
	}
}
