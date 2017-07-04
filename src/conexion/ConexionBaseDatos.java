package conexion;
import java.sql.*;
public class ConexionBaseDatos {
	
	public static void main(String args[]){
		
		ConexionBaseDatos cn = new ConexionBaseDatos();
		cn.obtenerConexionBDTienda("PixelSqlbase");
	}

	public Connection obtenerConexionBDPrincipal(){
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    

		} catch (Exception e) {

		    System.out.println(e.toString());

		}
		
		Connection con = null;
		//...

		try {

		    con = DriverManager.getConnection(
		            "jdbc:mysql://localhost/pizzaamericana?"
		            + "user=root&password=naillive");

		    // Otros y operaciones sobre la base de datos...

		} catch (SQLException ex) {

		    // Mantener el control sobre el tipo de error
		    System.out.println("SQLException: " + ex.getMessage());

		}
		return(con);
	}
	
	public void obtenerConexionBDTienda(String dsn){
		try {

			//Class.forName("sybase.jdbc.sqlanywhere.IDriver");
			 Connection con = DriverManager.getConnection("jdbc:sqlanywhere:dsn="+dsn+";uid=admin;pwd=xxx");
			 //String dburl = "jdbc:sqlanywhere:uid=admin;pwd=xxx;eng=devdb;database=Pixel";
			 //jdbc:sqlanywhere:uid=DBA;pwd=sql;eng=demo
			 //Connection con = DriverManager.getConnection(dburl);
			 Statement statement = con.createStatement();
			 ResultSet rs = statement.executeQuery("select count(*) from dba.employee");
			 if (rs.next()) {
				 int numeroEmpleados = rs.getInt(0); // get first column returned
				 System.out.println("NumeroTotalEmpleados : " + numeroEmpleados );
			 }

		} catch (SQLException ex) {

		    // Mantener el control sobre el tipo de error
		    System.out.println("SQLException: " + ex.getMessage());

		}
	}
	
}
