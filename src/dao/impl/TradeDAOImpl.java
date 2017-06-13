package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ITradeDAO;
import vo.Trade;



public class TradeDAOImpl implements ITradeDAO{
	private Connection conn= null;
	private PreparedStatement pstmt = null;
	public TradeDAOImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public boolean doCreate(Trade trade) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(trade.getTitle());
		boolean flag = false;
		String sql ="insert into trade(id,author,lightrade,title,responsetrade,time) values(?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, trade.getId());
		this.pstmt.setString(2, trade.getAuthor());
		this.pstmt.setInt(3, trade.getLighttrade());
		this.pstmt.setString(4, trade.getTitle());
		this.pstmt.setInt(5, trade.getReponsetrade());
		System.out.println(trade.getTime());
		this.pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
		

		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	@Override
	public List<Trade> findAll(String keyWord) throws Exception {
		List<Trade> all = new ArrayList<Trade>();
		String sql = "select * from trade order by ? DESC";
		//order by "+keyWord+ "DESC
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		ResultSet rs = this.pstmt.executeQuery();
		Trade trade =null;
		while(rs.next()){
			trade = new Trade();
			trade.setId(rs.getInt(1));
			trade.setAuthor(rs.getString(2));
			trade.setLighttrade(rs.getInt(3));
			trade.setTitle(rs.getString(4));
			trade.setReponsetrade(rs.getInt(5));
			trade.setTime(rs.getTimestamp(6));
			trade.setBbs_id(rs.getInt(7));
			all.add(trade);
		}
		this.pstmt.close();
		return all;
	}
	@Override
	public List<Trade> findById(int id) throws Exception {
		String sql ="select * from trade where id = ?";
		List<Trade> all = new ArrayList<Trade>();
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		Trade trade =null;
		while(rs.next()){
			trade = new Trade();
			trade.setId(rs.getInt(1));
			trade.setAuthor(rs.getString(2));
			trade.setLighttrade(rs.getInt(3));
			trade.setTitle(rs.getString(4));
			trade.setReponsetrade(rs.getInt(5));
			trade.setTime(rs.getTimestamp(6));
			trade.setBbs_id(rs.getInt(7));
			all.add(trade);
		}
		this.pstmt.close();
		return all;
	}
}
