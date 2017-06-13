package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import Util.DataBaseUtil;

import com.mysql.jdbc.Connection;

import entity.ResearchEntity;

public class BackResearchBean {

	public boolean textCheck(String str)
	{
		if(null==str||"".equals(str))
			return false;
		else {
			return true;
		}
	}
	
	
	public int insert(ResearchEntity e) {

		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="insert into researsh(subject,university,author,title,content,d) values (?,?,?,?,?,?)";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e.getSubject());
			pstmt.setString(2, e.getUniversity());
			pstmt.setString(3, e.getAuthor());
			pstmt.setString(4, e.getTitle());
			pstmt.setString(5, e.getContent());
			pstmt.setString(6, e.getDate());
			
			err=pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return err;
	}


	public int delete(String id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="delete from researsh where id=?";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(id));
			err=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return err;
	}
	
	public ResearchEntity query(String id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select * from researsh where id=?";	
		PreparedStatement pstmt;
		ResearchEntity entity=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			ResultSet r=pstmt.executeQuery();
			if(r.next())
			{
				entity=new ResearchEntity();
				entity.setAuthor(r.getString("author"));
				entity.setTitle(r.getString("title"));
				entity.setDate(r.getString("d"));
				entity.setId(r.getInt("id")+"");
				entity.setSubject(r.getInt("subject"));
				entity.setUniversity(r.getString("university"));
				entity.setContent(r.getString("content"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=null;
		}
		
		return entity;
		
	}
	
	public int update(ResearchEntity e) {

		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="update researsh set subject=?,university=?,author=?,title=?,content=?,d=? where id=?";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e.getSubject());
			pstmt.setString(2, e.getUniversity());
			pstmt.setString(3, e.getAuthor());
			pstmt.setString(4, e.getTitle());
			pstmt.setString(5, e.getContent());
			pstmt.setString(6, e.getDate());
			pstmt.setInt(7, Integer.parseInt(e.getId()));
			err=pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return err;
	}


}
