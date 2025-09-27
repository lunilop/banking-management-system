package daoImp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.TipoCuentas;
import dao.CuentaDao;
import entidades.Cuentas;
import entidades.Clientes;


public class CuentasDaoImp implements CuentaDao {
	private Conexion cn;
	
	public boolean borrar(int nroCuenta) {
		boolean estado=true;
		cn= new Conexion();
		cn.Open();
		String query = "UPDATE Cuentas SET estado=0 WHERE nroCuenta=" + nroCuenta;
		System.out.println(query);
		try {
			estado=cn.execute(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return estado;
	}
	
	public List<Cuentas> obtenerTodos(){
		cn= new Conexion();
		cn.Open();
		List<Cuentas> list = new ArrayList<Cuentas>();
		try
		{
			ResultSet rs= cn.query("SELECT c.nroCuenta , c.dni_Cu, c.fechaCreacion, tc.nombreCuenta, c.cbu, c.saldo,tc.idTipoCuenta FROM Cuentas c INNER JOIN tiposdecuentas tc ON tc.idTipoCuenta = c.idTipoCuenta_Cu WHERE c.estado = 1");
			while(rs.next()) {
				Cuentas cuenta = new Cuentas();
				cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				cuenta.setDni(rs.getString("dni_Cu"));
				Date fecha = rs.getDate("fechaCreacion");
				cuenta.setLocalDate(fecha.toLocalDate());
				
				TipoCuentas tc = new TipoCuentas();
				tc.setNombreCuenta(rs.getString("nombreCuenta"));
				tc.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				cuenta.setTipoDeCuenta(tc);
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				list.add(cuenta);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return list;
	}
	
	public boolean agregarCuenta(Cuentas cuenta) {
	    cn = new Conexion();
	    cn.Open();
	    boolean agregar = false;
	    
	    String consultaInsert = "INSERT INTO cuentas(nroCuenta, dni_Cu, fechaCreacion, idTipoCuenta_Cu, cbu, saldo, estado) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try {

	        PreparedStatement stmt = cn.connection.prepareStatement(consultaInsert);
	        stmt.setInt(1, cuenta.getNroCuenta());
	        stmt.setString(2, cuenta.getDni());
	        stmt.setDate(3, Date.valueOf(cuenta.getFechaCreacion()));
	        
	        stmt.setInt(4, cuenta.getTipoDeCuenta().getIdTipoCuenta());
	        
	        stmt.setString(5, cuenta.getCbu());
	        stmt.setFloat(6, cuenta.getSaldo());
	        stmt.setBoolean(7,cuenta.getEstado());

	        int filasInsertadas = stmt.executeUpdate();
	        agregar = filasInsertadas > 0;

	    } catch (SQLException e) {
	        System.out.println("Error de SQL: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return agregar;
	}
	
	public boolean modificarCuenta(Cuentas cu) {
		cn = new Conexion();
		cn.Open();
		boolean estado = false;
		String fechaCreacionStr = cu.getFechaCreacion().toString(); 
		String consulta = "UPDATE Cuentas SET dni_Cu = '" + cu.getDni() +
		             "', fechaCreacion  = '" + fechaCreacionStr +
		             "', idTipoCuenta_Cu = '" + cu.getTipoDeCuenta().getIdTipoCuenta() +
		             "', cbu  = '" + cu.getCbu()+
		             "', saldo  = '" + cu.getSaldo() +
		             "' WHERE nroCuenta = '" + cu.getNroCuenta() + "'";
		try{
			estado=cn.execute(consulta);
		 }
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		return estado;
	}

	public List<Cuentas> listarCuentasPorDni(String dni) {
		cn = new Conexion();
		cn.Open();
		List<Cuentas> list = new ArrayList<Cuentas>();
		String consulta = "SELECT nroCuenta,dni_Cu,fechaCreacion,tiposdecuentas.nombreCuenta AS tipoCuenta,cbu,saldo FROM Cuentas INNER JOIN tiposDeCuentas ON Cuentas.idTipoCuenta_Cu = tiposdecuentas.idTipoCuenta WHERE estado=1 and dni_Cu like '"+dni+"%'";
		 
		try{
			ResultSet rs= cn.query(consulta);
			while(rs.next())
			{
				Cuentas cuenta = new Cuentas();
				cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				cuenta.setDni(rs.getString("dni_Cu"));
				Date fecha = rs.getDate("fechaCreacion");
				
				TipoCuentas tipoCuenta = new TipoCuentas();
	            tipoCuenta.setNombreCuenta(rs.getString("tipoCuenta"));
	            cuenta.setTipoDeCuenta(tipoCuenta);
	            
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				list.add(cuenta);
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			 cn.close();
		 }
		 return list;
	}


	public List<Cuentas> listarCuentasPorTipoCuenta(int TipoCuenta){
		cn = new Conexion();
		cn.Open();
		 List<Cuentas> list = new ArrayList<Cuentas>();
		 String consulta = "SELECT nroCuenta,dni_Cu,fechaCreacion,tiposdecuentas.nombreCuenta AS tipoCuenta,cbu,saldo FROM Cuentas INNER JOIN tiposDeCuentas ON Cuentas.idTipoCuenta_Cu = tiposdecuentas.idTipoCuenta WHERE estado=1 and Cuentas.idTipoCuenta_Cu = '"+TipoCuenta+"'";
		 try
		 {
			 ResultSet rs= cn.query(consulta);
			 while(rs.next())
			 {
				 Cuentas cuenta = new Cuentas();
				 cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				 cuenta.setDni(rs.getString("dni_Cu"));
				 Date fecha = rs.getDate("fechaCreacion");
				 
				 TipoCuentas tipoCuenta = new TipoCuentas();
		         tipoCuenta.setNombreCuenta(rs.getString("tipoCuenta"));
		         cuenta.setTipoDeCuenta(tipoCuenta);
				 
				 cuenta.setCbu(rs.getString("cbu"));
				 cuenta.setSaldo(rs.getFloat("saldo"));
				 list.add(cuenta);
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			 cn.close();
		 }
		 return list;
	}
	
	public int contarCuentasPorDni(String dni) {
	    cn = new Conexion();
	    cn.Open();
	    int cantidadCuentas = 0;
	    String consulta = "SELECT COUNT(*) AS cantidad FROM Cuentas WHERE estado = 1 AND dni_Cu = ?";
	    
	    try {
	        PreparedStatement stmt = cn.connection.prepareStatement(consulta);
	        stmt.setString(1, dni);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            cantidadCuentas = rs.getInt("cantidad");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error de SQL: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return cantidadCuentas;
	}

	/*public List<Cuentas> obtenerCuentasPorDni(String dni) {
	    List<Cuentas> cuentas = new ArrayList<Cuentas>();
	    cn = new Conexion();
	    cn.Open();
	    
	    try {
	        String consulta = "SELECT  c.idTipoCuenta_Cu, tc.nombreCuenta FROM (Cuentas c INNER JOIN Clientes cl  ON c.dni_Cu = cl.dni) INNER JOIN tiposdecuentas tc ON  c.idTipoCuenta_Cu = tc.idTipoCuenta WHERE cl.dni = ?";
	        
	        PreparedStatement stmt = cn.connection.prepareStatement(consulta);
	        stmt.setString(1, dni);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	Cuentas cuenta = new Cuentas();
				cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
	            
	            cuentas.add(cuenta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return cuentas;
	}*/
	
	public List<Integer> obtenerNumerosDeCuentaPorDni(String dni) {
	    List<Integer> numerosDeCuenta = new ArrayList<>();
	    cn = new Conexion();
	    cn.Open();
	    
	    try {
	        String consulta = "SELECT c.nroCuenta FROM Cuentas c INNER JOIN Clientes cl ON c.dni_Cu = cl.dni WHERE cl.dni = ?";
	        PreparedStatement stmt = cn.connection.prepareStatement(consulta);
	        stmt.setString(1, dni);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            numerosDeCuenta.add(rs.getInt("nroCuenta"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return numerosDeCuenta;
	}

}
