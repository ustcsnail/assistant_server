package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.DataBaseUtil;

import com.mysql.jdbc.Connection;

import entity.Director;
import entity.User;

public class Adminbean {

	public boolean  check(User user)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		
		String sql="select * from admin where username=?";	
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			ResultSet r=pstmt.executeQuery();
			
			if(r.next())
			{
				String passwd=r.getString("password");
				
				if(user.getPasswd().equals(passwd))
					return true;
				else
					return false;
			}
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
}
