package capaDAO;

import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import capaModelo.Cliente;
import capaModelo.Tienda;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
public class ClienteDAO {
	
	public static ArrayList<Cliente> obtenerCliente(String tel)
	{
		Logger logger = Logger.getLogger("log_file");
		ArrayList<Cliente> clientes = new ArrayList();
		ConexionBaseDatos con = new ConexionBaseDatos();
		Connection con1 = con.obtenerConexionBDPrincipal();
		try
		{
			Statement stm = con1.createStatement();
			String consulta = "select a.idcliente, b.nombre nombreTienda, a.idtienda, a.nombre, a.direccion, a.zona, a.observacion, a.telefono, c.nombre nombremunicipio, a.latitud, a.longitud from cliente a,tienda b, municipio c where a.idtienda = b.idtienda and a.idmunicipio = c.idmunicipio and a.telefono = '" + tel +"'";
			logger.info(consulta);
			ResultSet rs = stm.executeQuery(consulta);
			while(rs.next()){
				int idcliente = rs.getInt("idcliente");
				String nombreTienda = rs.getString("nombreTienda");
				String nombreCliente = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String zona = rs.getString("zona");
				String observacion = rs.getString("observacion");
				String telefono = rs.getString("telefono");
				String municipio = rs.getString("nombremunicipio");
				float latitud = rs.getFloat("latitud");
				float longitud = rs.getFloat("longitud");
				int idTienda = rs.getInt("idtienda");
				Cliente clien = new Cliente( idcliente, telefono, nombreCliente, direccion,municipio,latitud, longitud, zona, observacion, nombreTienda, idTienda);
				clientes.add(clien);
			}
		}catch (Exception e){
			logger.error(e.toString());
		}
		return(clientes);
		
	}
	
	public static int insertarCliente(Cliente clienteInsertar)
	{
		Logger logger = Logger.getLogger("log_file");
		int idClienteInsertado = 0;
		ConexionBaseDatos con = new ConexionBaseDatos();
		Connection con1 = con.obtenerConexionBDPrincipal();
		try
		{
			Statement stm = con1.createStatement();
			String insert = "insert into cliente (idtienda,nombre, direccion, zona, telefono, observacion, municipio, latitud, longitud) values (" + clienteInsertar.getIdtienda() + ", '" +clienteInsertar.getNombres() + "' , '" + clienteInsertar.getDireccion() + "' , '" + clienteInsertar.getZonaDireccion() +"' , '" + clienteInsertar.getTelefono() + "' , '" + clienteInsertar.getObservacion() + " , " + clienteInsertar.getIdMunicipio() + " , " + clienteInsertar.getLatitud() + " , " + clienteInsertar.getLontitud() + "')"; 
			logger.info(insert);
			stm.executeUpdate(insert);
			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()){
				idClienteInsertado=rs.getInt(1);
				
	        }
			stm.close();
			con1.close();
		}
		catch (Exception e){
			logger.error(e.toString());
			return(0);
		}
		return(idClienteInsertado);
	}
	
	
	public static Cliente obtenerClienteporID(int id)
	{
		Logger logger = Logger.getLogger("log_file");
		Cliente clienteConsultado = new Cliente(); 
		ConexionBaseDatos con = new ConexionBaseDatos();
		Connection con1 = con.obtenerConexionBDPrincipal();
		try
		{
			Statement stm = con1.createStatement();
			String consulta = "select a.idcliente, b.nombre nombreTienda, a.idtienda, a.nombre, a.direccion, a.zona, a.observacion, a.telefono, c.nombre nombremunicipio, a.latitud, a.longitud from cliente a,tienda b, municipio c where a.idtienda = b.idtienda and a.idmunicipio = c.idmunicipio and a.idcliente = " + id +"";
			logger.info(consulta);
			ResultSet rs = stm.executeQuery(consulta);
			while(rs.next()){
				int idcliente = rs.getInt("idcliente");
				String nombreTienda = rs.getString("nombreTienda");
				String nombreCliente = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String zona = rs.getString("zona");
				String observacion = rs.getString("observacion");
				String telefono = rs.getString("telefono");
				String municipio = rs.getString("nombremunicipio");
				float latitud = rs.getFloat("latitud");
				float longitud = rs.getFloat("longitud");
				int idTienda = rs.getInt("idtienda");
				clienteConsultado = new Cliente( idcliente, telefono, nombreCliente, direccion,municipio,latitud, longitud, zona, observacion, nombreTienda, idTienda);
				
			}
		}catch (Exception e){
			logger.error(e.toString());
		}
		return(clienteConsultado);
		
	}
	
	public static int actualizarCliente(Cliente clienteAct)
	{
		Logger logger = Logger.getLogger("log_file");
		int idClienteActualizado = 0;
		ConexionBaseDatos con = new ConexionBaseDatos();
		Connection con1 = con.obtenerConexionBDPrincipal();
		try
		{
			Statement stm = con1.createStatement();
			String update = "update cliente set nombre = '" + clienteAct.getNombres() + "' , direccion = '" + clienteAct.getDireccion() + "' , idmunicipio = " + clienteAct.getIdMunicipio() + " , latitud = " + clienteAct.getLatitud() + " , longitud = " + clienteAct.getLontitud() + " , zona = '" + clienteAct.getZonaDireccion() + "' , observacion = '" + clienteAct.getObservacion() +"'  where telefono = '" + clienteAct.getTelefono() + "'  and idtienda = '" + clienteAct.getIdtienda() + "'"; 
			logger.info(update);
			stm.executeUpdate(update);
			String idClienteUpdate = "select idcliente from cliente where telefono = '" + clienteAct.getTelefono() + "'  and idtienda = '" + clienteAct.getIdtienda() + "'";
			ResultSet rs = stm.executeQuery(idClienteUpdate);
			while(rs.next()){
				idClienteActualizado = rs.getInt("idcliente");
				break;
			}
			stm.close();
			con1.close();
		}
		catch (Exception e){
			logger.error(e.toString());
			return(0);
		}
		logger.info("id cliente actualizado" + idClienteActualizado);
		return(idClienteActualizado);
	}
	
	

}
