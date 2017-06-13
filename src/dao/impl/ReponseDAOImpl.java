package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  dao.IResponseDAO;
import  vo.BBS;
import  vo.ResponseBBS;

public class ReponseDAOImpl implements IResponseDAO {
	private Connection conn= null;
	private PreparedStatement pstmt = null;
	public  ReponseDAOImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public boolean doCreate(ResponseBBS bbs) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(bbs.getTitle());
		boolean flag = false;
		String sql ="insert into reponseBBS(author,title,BBS_id,time,id) values(?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, bbs.getAuthor());
		this.pstmt.setString(2, bbs.getTitle());
		this.pstmt.setInt(3, bbs.getBBS_id());
		this.pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
		this.pstmt.setInt(5, bbs.getId());
		
		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	@Override
	public List<ResponseBBS> findAll(int id) throws Exception {
		List<ResponseBBS> all = new ArrayList<ResponseBBS>();
		String sql = "select * from reponseBBS  where BBS_id=? order by time DESC";
		//order by "+keyWord+ "DESC
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		ResponseBBS bbs =null;
		while(rs.next()){
			bbs = new ResponseBBS();
			bbs.setAuthor(rs.getString(1));
			bbs.setTitle(rs.getString(2));
			bbs.setBBS_id(rs.getInt(3));
			bbs.setLightBBS(rs.getInt(4));
			bbs.setTime(rs.getTimestamp(5));
			bbs.setId(rs.getInt(6));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
	@Override
	public List<ResponseBBS> findById(int id) throws Exception {
		String sql ="select * from reponseBBS where id = ?";
		List<ResponseBBS> all = new ArrayList<ResponseBBS>();
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		ResponseBBS bbs =null;
		while(rs.next()){
			bbs = new ResponseBBS();
			bbs.setAuthor(rs.getString(1));
			bbs.setTitle(rs.getString(2));
			bbs.setBBS_id(rs.getInt(3));
			bbs.setLightBBS(rs.getInt(4));
			bbs.setTime(rs.getTimestamp(5));
			bbs.setId(rs.getInt(6));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
}
