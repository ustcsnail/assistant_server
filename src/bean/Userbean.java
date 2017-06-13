package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Util.DataBaseUtil;

import com.mysql.jdbc.Statement;

import entity.User;

public class Userbean {

	private User user=null;
	
	public Userbean(User u)
	{
		user=u;
	}
	
	public boolean registerCheck()
    {
      
        if(null!=user.getName()&&!"".equals(user.getName())&&null!=user.getPasswd()&&!"".equals(user.getPasswd())&&null!=user.getTele()&&!"".equals(user.getTele()))
		{
			return true;
		}
		else
			return false;
    }
	
	public int insert()
	{
		int err=0;
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		
		String sql="insert into user(name,password,tele) values(?,?,?)";
		try {
			java.sql.PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getTele());
			err=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return err;
	}
	
	public int update(String sql,String value)
	{
		int err=0;
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		
		try {
			java.sql.PreparedStatement pstmt=conn.prepareStatement(sql);			
			pstmt.setString(1, value);
			err=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return err;
	}
	
	
	public boolean loginCheck()
	{
		if(null!=user.getPasswd()&&!"".equals(user.getPasswd())&&null!=user.getTele()&&!"".equals(user.getTele()))
		{
			user.setTele(user.getTele().trim());
			user.setPasswd(user.getPasswd().trim());
			return true;
		}
		else
			return false;
	}
	
	public User login()
	{
		int err=0;
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		
		String sql="select * from user where tele=?";		
		try {
			java.sql.PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getTele());
			ResultSet r=pstmt.executeQuery();
			//����tele��uniqueԼ�������Խ������ֻ��һ���������û�н��
			if(r.next())
			{
				//���ڽ��
				//r.previous();
				user.setId(r.getString("id"));
				user.setName(r.getString("name"));  
				String passwd=null;
				passwd=r.getString("password");
				if(passwd.trim().equals(user.getPasswd()))		
				{
					user.setStatus(true);
					user.setPasswd("");
				}
					
				else
				{
					user.setStatus(false);
					user.setPasswd("�������");					
				}
				
				return user;
			}
			else
			{
				//�����ڽ��
				user.setStatus(false);
				user.setPasswd("�˺Ų�����");
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setStatus(false);
		user.setPasswd("����������");
		
		return user;
		
	}
	
	
	public List<User> query()
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		
		String sql="select * from user";
		java.sql.PreparedStatement pstmt;
		List<User> list=new ArrayList<User>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet r=pstmt.executeQuery();
			
			User user=null;
			while(r.next())
			{
				user=new User();
				user.setId(r.getInt("id")+"");
				user.setName(r.getString("name"));
				user.setPasswd(r.getString("password"));
				user.setTele(r.getString("tele"));
				list.add(user);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return list;	
	}

	public int delete(int id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="delete from user where id=?";	
		java.sql.PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			err=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return err;
	}
	
}
