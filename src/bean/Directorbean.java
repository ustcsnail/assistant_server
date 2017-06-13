package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.DataBaseUtil;

import com.mysql.jdbc.Connection;

import entity.Director;
import entity.Restaurant;

public class Directorbean {

	public boolean checkType(String type)
	{
		if(null==type||"".equals(type))
			return false;
		if(type.equals(Director.GOV)||type.equals(Director.LiFE))
			return true;
		else
			return false;
			
	}
	public boolean textCheck(String str)
	{
		if(null==str||"".equals(str))
			return false;
		else {
			return true;
		}
	}
	
	public String  query(String type)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		Director d=null;
		String sql="select * from director where type=?";	
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			ResultSet r=pstmt.executeQuery();
			
			JSONArray jsonArray=new JSONArray();
			JSONObject obj=null;
			if(r.next())
			{
				//´æÔÚ½á¹û
				r.previous();
				while(r.next())
				{
					obj=new JSONObject();
					obj.put("id", r.getInt("id"));
					obj.put("title", r.getString("title"));
					obj.put("content",r.getString("content"));
					jsonArray.put(obj);
				}
				obj=new JSONObject();
			    obj.put("result", Director.RESULT_SUCCESS);
				obj.put("array", jsonArray);	
				System.out.print(obj.toString());
			}
			else
			{	obj=new JSONObject();		
				obj.put("result", Director.RESULT_EMPTYERR);	
			}
			
			return obj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}

	public Director querySingle(int id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select * from director where id=?";	
		PreparedStatement pstmt;
		Director entity=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet r=pstmt.executeQuery();
			if(r.next())
			{
				entity=new Director();
				entity.setId(r.getInt("id")+"");
				entity.setTitle(r.getString("title"));
				entity.setContent(r.getString("content"));			
				entity.setType(r.getString("type"));			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=null;
		}
		
		return entity;
	}

	public int delete(int id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="delete from director where id=?";	
		PreparedStatement pstmt;
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


	 public int update(Director e) 
	    {
	    	DataBaseUtil db=DataBaseUtil.getUtil();
			Connection conn=db.getConnection();
			String sql="update director set title=?,type=?,content=? where id=?";	
			PreparedStatement pstmt;
			int err=0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, e.getTitle());
				pstmt.setString(2, e.getType());
				pstmt.setString(3, e.getContent());
				pstmt.setInt(4, Integer.parseInt(e.getId()));
				
				err=pstmt.executeUpdate();
				
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
			return err;
		
		}

	public int insert(Director e)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="insert into director(title,type,content) values (?,?,?)";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getTitle());
			pstmt.setString(2, e.getType());
			pstmt.setString(3, e.getContent());
			
			err=pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return err;	
	}
}
