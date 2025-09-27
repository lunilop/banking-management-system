package negocio;

import java.util.ArrayList;

import entidades.Movimientos;

public interface MovimientosNeg {
	public ArrayList<Movimientos> listarMovimientos(String dni);
}
