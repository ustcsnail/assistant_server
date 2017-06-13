package Util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;



public class DataBaseUtil 
{
	private static DataBaseUtil dbutil=null;
	
	private  Connection conn = null;
	
	private DataBaseUtil()
	{		
	}
	
	private  void getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/assistant";
	    String username = "root";
	    String password = "111111";
	    
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static DataBaseUtil getUtil()
	{
		if(dbutil==null)
		{
			dbutil=new DataBaseUtil();
			dbutil.getConn();
		}
		return dbutil;
		
	}
	
	public boolean insert(String sql)
	{
		return true;
	}

	public Connection getConnection() {
		return conn;
	}
	
	
	
	
}
