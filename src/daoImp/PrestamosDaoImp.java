package daoImp;
import dao.PrestamosDao;
import entidades.Prestamos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class PrestamosDaoImp implements PrestamosDao{
	private Conexion cn;
	
	public List<Prestamos> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Prestamos> list = new ArrayList<Prestamos>();
		 try
		 {
			 ResultSet rs= cn.query("SELECT idPrestamo,dni_Pr,nroCuenta_Pr,fechaPrestamo,importeConIntereses,importePedidoCliente,plazoPago,cantidadCuotas,montoPorMes,estado FROM Prestamos WHERE estado='pendiente'");
			 while(rs.next())
			 {
				 Prestamos p = new Prestamos();
				 p.setIdPrestamo(rs.getInt("idPrestamo"));
				 p.setDni(rs.getString("dni_Pr"));
				 p.setNroCuenta(rs.getInt("nroCuenta_Pr"));
				 Date fecha = rs.getDate("fechaPrestamo");
				 p.setFechaNacimiento(fecha.toLocalDate());
				 p.setImporteConIntereses(rs.getDouble("importeConIntereses"));
				 p.setImportePedidoPorCliente(rs.getDouble("importePedidoCliente"));
				 p.setPlazoPago(rs.getInt("plazoPago"));
				 p.setCantidadCuotas(rs.getInt("cantidadCuotas"));
				 p.setMontoPorMes(rs.getDouble("montoPorMes"));
				 p.setEstado(rs.getString("estado"));
				 
				 list.add(p);
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
	
	public boolean actualizarEstado(Prestamos prestamo) {
	    String sql = "UPDATE Prestamos SET estado = ? WHERE idPrestamo = ?";
	    cn = new Conexion(); // Instanciar la conexión
	    cn.Open(); // Abrir la conexión manualmente

	    boolean actualizado = false;

	    try {
	        PreparedStatement ps = (PreparedStatement) cn.connection.prepareStatement(sql);
	        ps.setString(1, prestamo.getEstado());
	        ps.setInt(2, prestamo.getIdPrestamo());

	        actualizado = ps.executeUpdate() > 0; // Verificar si se actualizó alguna fila
	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejo de errores
	    } finally {
	        cn.close(); // Cerrar la conexión manualmente
	    }

	    return actualizado;
	}
}
