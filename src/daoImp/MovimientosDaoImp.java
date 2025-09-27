package daoImp;
import entidades.Clientes;
import entidades.Movimientos;
import entidades.TipoMovimiento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientosDao;

public class MovimientosDaoImp implements MovimientosDao{
	private Conexion cn;
	
	public List<Movimientos> obtenerTodos(String dni) {
		cn = new Conexion();
		cn.Open();
		 List<Movimientos> list = new ArrayList<Movimientos>();
		 try
		 {
			 String consulta="SELECT m.idMovimiento, m.nroCuenta_M, m.fecha, m.detalle, m.importe, tm.nombre AS tipoMovimiento FROM ((Movimientos m INNER JOIN Cuentas c ON m.nroCuenta_M = c.nroCuenta) INNER JOIN Clientes cl ON c.dni_Cu = cl.dni) INNER JOIN tiposDeMovimientos tm ON m.idTipoMovimiento_M = tm.idTipoMovimiento WHERE  cl.dni = ?";
			 PreparedStatement stmt = cn.connection.prepareStatement(consulta);
		     stmt.setString(1, dni);
		     ResultSet rs = stmt.executeQuery();
			 while(rs.next())
			 {
				 Movimientos mov = new Movimientos();
				 mov.setIdMovimiento(rs.getInt("idMovimiento"));
				 mov.setNroCuenta_M(rs.getInt("nroCuenta_M"));
				 Date fecha = rs.getDate("fecha");
				 mov.setFecha(fecha.toLocalDate());
				 mov.setDetalle(rs.getString("detalle"));
				 mov.setImporte(rs.getDouble("importe"));
				 
				 TipoMovimiento tmov = new TipoMovimiento();
				 tmov.setNombre(rs.getString("tipoMovimiento"));
		         
				 mov.setTipoDeMovimiento(tmov);
				 
				 list.add(mov);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return list;
	}
}
