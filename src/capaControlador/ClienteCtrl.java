package capaControlador;
import capaModelo.Cliente;
import capaModelo.Tienda;
import capaDAO.ClienteDAO;
import capaDAO.MunicipioDAO;
import capaDAO.TiendaDAO;

import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.*;

public class ClienteCtrl {
	
	public String obtenerCliente(String tel){
		JSONArray listJSON = new JSONArray();
		ArrayList<Cliente> clientes = ClienteDAO.obtenerCliente(tel);
		for (Cliente cliente : clientes) {
			JSONObject cadaViajeJSON = new JSONObject();
			cadaViajeJSON.put("idCliente", cliente.getIdcliente());
			cadaViajeJSON.put("tienda", cliente.getTienda());
			cadaViajeJSON.put("nombre", cliente.getNombres());
			cadaViajeJSON.put("direccion", cliente.getDireccion());
			cadaViajeJSON.put("zona", cliente.getZonaDireccion());
			cadaViajeJSON.put("observacion", cliente.getObservacion());
			cadaViajeJSON.put("telefono", cliente.getTelefono());
			cadaViajeJSON.put("municipio", cliente.getMunicipio());
			cadaViajeJSON.put("longitud", cliente.getLontitud());
			cadaViajeJSON.put("latitud", cliente.getLatitud());
			listJSON.add(cadaViajeJSON);
		}
		//String temp = listJSON.toJSONString();
		//temp = temp.substring(0,1) + "\"cliente\":[" + temp.substring(1, temp.length()-1) + "]]";
		//System.out.println(temp);
		//System.out.println(listJSON.toJSONString());
		return listJSON.toJSONString();
	}
	
public String obtenerClienteporID(int id)
{
	Cliente clienteConsultado = ClienteDAO.obtenerClienteporID(id);
	JSONArray listJSON = new JSONArray();
	JSONObject Respuesta = new JSONObject();
	Respuesta.put("idcliente", clienteConsultado.getIdcliente());
	Respuesta.put("nombretienda", clienteConsultado.getTienda());
	Respuesta.put("nombrecliente", clienteConsultado.getNombres());
	Respuesta.put("direccion", clienteConsultado.getDireccion());
	Respuesta.put("zona", clienteConsultado.getZonaDireccion());
	Respuesta.put("observacion", clienteConsultado.getObservacion());
	Respuesta.put("telefono", clienteConsultado.getTelefono());
	Respuesta.put("nombremunicipio", clienteConsultado.getMunicipio());
	Respuesta.put("latitud", clienteConsultado.getLatitud());
	Respuesta.put("longitud", clienteConsultado.getLontitud());
	Respuesta.put("idtienda", clienteConsultado.getIdtienda());
	listJSON.add(Respuesta);
	return(listJSON.toJSONString());
}
	
	
public String InsertarClientePedido(String telefono, String nombres, String direccion,  String zona,  String observacion, String tienda)
{
	//Validar si el cliente ya existe en la base de datos
	//Llamamos el método ya existente para saber si el cliente con el teléfono ya existe
	// Esta pendiente convertir el nombre tienda a tienda
	String retorno = "false";
	int idTienda = TiendaDAO.obteneridTienda(tienda);
	
	Cliente clienteInsertar = new Cliente(telefono, nombres, direccion, zona,  observacion,  tienda, idTienda);
	ArrayList<Cliente> clientes = ClienteDAO.obtenerCliente(telefono);
	boolean clienteTiendaExiste = false;
	int idRespuestaCreacion = 0;
	int idRespuestaActualizacion = 0;
	if (clientes.size() > 0)
	{
		for (Cliente cliente : clientes) 
		{
			System.out.println(cliente.getTienda() + clienteInsertar.getTienda());
			if (cliente.getTienda().equals(clienteInsertar.getTienda()))
			{
				clienteTiendaExiste = true;
				idRespuestaActualizacion = ClienteDAO.actualizarCliente(clienteInsertar);
				break;
			}
			
		}
		if (clienteTiendaExiste == false)
		{
			idRespuestaCreacion = ClienteDAO.insertarCliente(clienteInsertar);
		}
	}
	else
	{
		//Deberemos insertar el cliente en la base de datos
		idRespuestaCreacion = ClienteDAO.insertarCliente(clienteInsertar);
	}
	if (idRespuestaActualizacion > 0 || idRespuestaCreacion > 0 )
	{
		retorno = "true";
	}
	JSONArray listJSON = new JSONArray();
	JSONObject Respuesta = new JSONObject();
	Respuesta.put("resultado", retorno);
	if (idRespuestaActualizacion > 0)
	{
		Respuesta.put("idcliente", idRespuestaActualizacion);
	}else
	{
		if (idRespuestaCreacion > 0)
		{
			Respuesta.put("idcliente", idRespuestaCreacion);
		}
	}
	listJSON.add(Respuesta);
	return(listJSON.toJSONString());
}

public int InsertarClientePedidoEncabezado(String telefono, String nombres, String direccion, String municipio, float latitud, float longitud, String zona,  String observacion, String tienda)
{
	//Validar si el cliente ya existe en la base de datos
	//Llamamos el método ya existente para saber si el cliente con el teléfono ya existe
	// Esta pendiente convertir el nombre tienda a tienda
	int idTienda = TiendaDAO.obteneridTienda(tienda);
	int idMunicipio = MunicipioDAO.obteneridMunicipio(municipio);
	Cliente clienteInsertar = new Cliente(telefono, nombres, direccion,municipio, idMunicipio, latitud, longitud, zona,  observacion,  tienda, idTienda);
	ArrayList<Cliente> clientes = ClienteDAO.obtenerCliente(telefono);
	boolean clienteTiendaExiste = false;
	int idRespuestaCreacion = 0;
	int idRespuestaActualizacion = 0;
	if (clientes.size() > 0)
	{
		for (Cliente cliente : clientes) 
		{
			System.out.println(cliente.getTienda() + clienteInsertar.getTienda());
			if (cliente.getTienda().equals(clienteInsertar.getTienda()))
			{
				clienteTiendaExiste = true;
				idRespuestaActualizacion = ClienteDAO.actualizarCliente(clienteInsertar);
				break;
			}
			
		}
		if (clienteTiendaExiste == false)
		{
			idRespuestaCreacion = ClienteDAO.insertarCliente(clienteInsertar);
		}
	}
	else
	{
		//Deberemos insertar el cliente en la base de datos
		idRespuestaCreacion = ClienteDAO.insertarCliente(clienteInsertar);
	}
	
	if (idRespuestaActualizacion > 0)
	{
		return(idRespuestaActualizacion);
	}else
	{
		if (idRespuestaCreacion > 0)
		{
			return(idRespuestaCreacion);
		}
	}
	return(0);
}

}
