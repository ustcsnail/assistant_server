package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.IResponseTrade;
import vo.ResponseBBS;
import vo.ResponseTrade;



public class ResponseTradeImpl implements IResponseTrade {
	private Connection conn= null;
	private PreparedStatement pstmt = null;
	public  ResponseTradeImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public boolean doCreate(ResponseTrade bbs) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(bbs.getTitle());
		boolean flag = false;
		String sql ="insert into reponsetrade(author,title,trade_id,time,id) values(?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, bbs.getAuthor());
		this.pstmt.setString(2, bbs.getTitle());
		this.pstmt.setInt(3, bbs.getTrade_id());
		this.pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
		this.pstmt.setInt(5, bbs.getId());
		
		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	@Override
	public List<ResponseTrade> findAll(int id) throws Exception {
		List<ResponseTrade> all = new ArrayList<ResponseTrade>();
		String sql = "select * from reponsetrade  where trade_id=? order by time DESC";
		//order by "+keyWord+ "DESC
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		ResponseTrade bbs =null;
		while(rs.next()){
			bbs = new ResponseTrade();
			bbs.setAuthor(rs.getString(1));
			bbs.setTitle(rs.getString(2));
			bbs.setTrade_id(rs.getInt(3));
			bbs.setLighttrade(rs.getInt(4));
			bbs.setTime(rs.getTimestamp(5));
			bbs.setId(rs.getInt(6));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
	@Override
	public List<ResponseTrade> findById(int id) throws Exception {
		String sql ="select * from reponsetrade where id = ?";
		List<ResponseTrade> all = new ArrayList<ResponseTrade>();
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		ResponseTrade bbs =null;
		while(rs.next()){
			bbs = new ResponseTrade();
			bbs.setAuthor(rs.getString(1));
			bbs.setTitle(rs.getString(2));
			bbs.setTrade_id(rs.getInt(3));
			bbs.setLighttrade(rs.getInt(4));
			bbs.setTime(rs.getTimestamp(5));
			bbs.setId(rs.getInt(6));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
}
