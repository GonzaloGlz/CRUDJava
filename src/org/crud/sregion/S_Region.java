package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*CRUD: CREATE  - READ  -  UPDATE  -  DELETE
 * 
 * PRIMER CRUD
 * 
 * */

public class S_Region {
	
	//CONEXION A LA BASE DE DATOS
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public static void connectDataBaseOrcale() throws IOException,SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("ACRGO DRIVER CORRECTAMENTE: ojdbc6.jar");
		} catch (Exception e) {
		   System.out.println("Exception:" + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL, "SYSTEM", "12345");
			System.out.println("CONEXION EXITOSA!!! : ORACLE 11G");
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		
	}
	
	//INSERTAR
	public static void insertarS_Region(int id, String name) throws IOException, SQLException{
		try {
			connectDataBaseOrcale();
			String sql = "INSERT INTO S_REGION (ID, NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			System.out.println("INSERTO EL REGISTRO :" + id + " , " + name);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	
	//ACTUALIZAR
	public static void updateS_Region(String name, int id) throws IOException, SQLException{
		try {
			connectDataBaseOrcale();
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			System.out.println("ACTUALIZO EL REGISTRO :" + id + " , " + name);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	
	//ELIMINAR
		public static void deleteS_Region(int id) throws IOException, SQLException{
			try {
				connectDataBaseOrcale();
				String sql = "DELETE FROM S_REGION WHERE ID = ? ";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeQuery();
				System.out.println("ELIMINO EL REGISTRO :" + id );
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
			}
		}
		
		//SELECCIONAR REGISTRO POR ID
		public static void selectS_Region(int id) throws IOException, SQLException{
			try {
				connectDataBaseOrcale();
				String sql = "SELECT * FROM S_REGION WHERE ID = ?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
                ResultSet rSet = ps.executeQuery();
                if (rSet.next()) {
                	System.out.println(rSet.getInt("id")+" , " + rSet.getString("name"));
                }
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
			}
		}
	 
		//SELECCIONAR TODOS LOS REGISTROS
		public static void selectAllS_Region() throws IOException, SQLException{
			try {
				connectDataBaseOrcale();
				String sql = "SELECT * FROM S_REGION";
				PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rSet = ps.executeQuery();
                while (rSet.next()) {
                	System.out.println(rSet.getInt("id")+" , " + rSet.getString("name"));
                }
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
			}
		}
		
		//INVOCACION DEL PROCEDIMIENTO ALMACENADO PROC
		public static void invocateProcedureS_Region(int id, String name) throws IOException, SQLException {
			try {
				connectDataBaseOrcale();
				String sql = "CALL proc(?,?)";
				CallableStatement cs = connection.prepareCall(sql);
				cs.setInt(1, id);
				cs.setString(2, name);
				cs.execute();
				System.out.println("EJECUTO CORRECTAMENTE EL PROCEDIMIENTO");
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			
		}
		
	public static void main(String[] args) throws IOException, SQLException{
		//connectDataBaseOrcale();//PARA VERIFICAR QUE LA CONEXION FUE EXITOSA
		//insertarS_Region(14, "OAXACA");
		//updateS_Region("MICHOACAN" , 14);
		//deleteS_Region(14);
		//selectS_Region(1);
		//selectAllS_Region();
		invocateProcedureS_Region(14, "OAXACA");
		
	}

}










