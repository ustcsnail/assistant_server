package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import Util.DataBaseUtil;

import com.mysql.jdbc.Connection;

import entity.ResearchEntity;

public class Collegebean {

	public boolean checkType(int subject)
	{
		
		if(subject==ResearchEntity.TYPE_LI||subject==ResearchEntity.TYPE_GONG
				||subject==ResearchEntity.TYPE_MEDICAL||subject==ResearchEntity.TYPE_ECONOMICS
				||subject==ResearchEntity.TYPE_SOCIETY||subject==ResearchEntity.TYPE_OTHERS
				||subject==ResearchEntity.TYPE_CONCRETE)
			return true;
		else
			return false;
			
	}
	
	public String query(String id)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select content from college where id=?";	
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			int i=Integer.parseInt(id);
			pstmt.setInt(1, i);
			ResultSet r=pstmt.executeQuery();

			JSONObject obj=null;
			
			if(r.next())
			{
					obj=new JSONObject();
					obj.put("content",r.getString("content"));
					obj.put("result", ResearchEntity.RESULT_SUCCESS);
				
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
	
	public String  query(int subject)
	{
		DataBaseUtil db=DataBaseUtil.getUtil();
		Connection conn=db.getConnection();
		String sql="select * from college where subject=?";	
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, subject);
			ResultSet r=pstmt.executeQuery();
			
			JSONArray jsonArray=new JSONArray();
			JSONObject obj=null;
			if(r.next())
			{
			
				//���ڽ��
				r.previous();
				while(r.next())
				{
					obj=new JSONObject();
					obj.put("id", r.getInt("id"));
					obj.put("subject", r.getInt("subject"));
					obj.put("university", r.getString("university"));
					obj.put("author", r.getString("author"));
					obj.put("title", r.getString("title"));
					//obj.put("content",r.getString("content"));
					obj.put("date",r.getString("d"));
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
}
