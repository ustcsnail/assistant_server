package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import sun.net.www.content.text.plain;
import Util.DataBaseUtil;

import com.mysql.jdbc.Connection;

import entity.ResearchEntity;
import entity.Restaurant;

public class Restaurantbean {

	public boolean textCheck(String str)
	{
		if(null==str||"".equals(str))
			return false;
		else {
			return true;
		}
	}
	
	public String query(int type)
	{
	
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select * from restaurant where type=?";	
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			ResultSet r=pstmt.executeQuery();

			JSONArray jsonArray=new JSONArray();
			JSONObject obj=null;
			
			if(r.next())
			{
				r.previous();
				while(r.next())
				{
					obj=new JSONObject();
					obj.put("id",r.getInt("id"));
					obj.put("name",r.getString("name"));
					obj.put("level",r.getInt("level"));
					obj.put("type",r.getInt("type"));
					obj.put("address",r.getString("address"));
					obj.put("district",r.getString("district"));
					obj.put("label",r.getString("label"));
					obj.put("mark", r.getInt("mark"));
					obj.put("price", r.getInt("price"));
					jsonArray.put(obj);
					
				}
				obj=new JSONObject();
			    obj.put("result", ResearchEntity.RESULT_SUCCESS);
				obj.put("array", jsonArray);	
				System.out.print(obj.toString());
			}
			else
			{	obj=new JSONObject();		
				obj.put("result", ResearchEntity.RESULT_EMPTYERR);	
			}
			return obj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}

	public Restaurant querySingle(int id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select * from restaurant where id=?";	
		PreparedStatement pstmt;
		Restaurant entity=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet r=pstmt.executeQuery();
			if(r.next())
			{
				entity=new Restaurant();
				entity.setId(r.getInt("id"));
				entity.setLevel(r.getInt("level"));
				entity.setType(r.getInt("type"));
				entity.setResterant_name(r.getString("name"));
				entity.setAddress(r.getString("address"));
				entity.setDistrict(r.getString("district"));			
				entity.setLabel(r.getString("label"));
				entity.setMark(r.getInt("mark"));
				entity.setPrice_avg(r.getInt("price"));
			
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
		String sql="delete from restaurant where id=?";	
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

    public int insert(Restaurant e)
    {
    	DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="insert into restaurant(name,level,type,address,district,label,mark,price) values (?,?,?,?,?,?,?,?)";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getResterant_name());
			pstmt.setInt(2, e.getLevel());
			pstmt.setInt(3, e.getType());
			pstmt.setString(4, e.getAddress());
			pstmt.setString(5, e.getDistrict());
			pstmt.setString(6, e.getLabel());
			pstmt.setInt(7, e.getMark());
			pstmt.setInt(8, e.getPrice_avg());
			
			err=pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return err;
    }


    public int update(Restaurant e) 
    {
    	DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="update restaurant set name=?,level=?,type=?,address=?,district=?,label=?, mark=?,price=? where id=?";	
		PreparedStatement pstmt;
		int err=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getResterant_name());
			pstmt.setInt(2, e.getLevel());
			pstmt.setInt(3, e.getType());
			pstmt.setString(4, e.getAddress());
			pstmt.setString(5, e.getDistrict());
			pstmt.setString(6, e.getLabel());
			pstmt.setInt(7, e.getMark());
			pstmt.setInt(8, e.getPrice_avg());
			pstmt.setInt(9, e.getId());
			err=pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return err;
	
	}
}
