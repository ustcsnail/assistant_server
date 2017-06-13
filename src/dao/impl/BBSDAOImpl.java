package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;
import dao.IBBSDAO;
import vo.BBS;





public class BBSDAOImpl implements IBBSDAO{
	private Connection conn= null;
	private PreparedStatement pstmt = null;
	public BBSDAOImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public boolean doCreate(BBS bbs) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(bbs.getTitle());
		boolean flag = false;
		String sql ="insert into BBS(id,author,lightBBS,title,responseBBS,time) values(?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, bbs.getId());
		this.pstmt.setString(2, bbs.getAuthor());
		this.pstmt.setInt(3, bbs.getLightBBS());
		this.pstmt.setString(4, bbs.getTitle());
		this.pstmt.setInt(5, bbs.getReponseBBS());
		System.out.println(bbs.getTime());
		this.pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
		

		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	@Override
	public List<BBS> findAll(String keyWord) throws Exception {
		List<BBS> all = new ArrayList<BBS>();
		String sql = "select * from BBS order by ? DESC";
		//order by "+keyWord+ "DESC
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		ResultSet rs = this.pstmt.executeQuery();
		BBS bbs =null;
		while(rs.next()){
			bbs = new BBS();
			bbs.setID(rs.getInt(1));
			bbs.setAuthor(rs.getString(2));
			bbs.setLightBBS(rs.getInt(3));
			bbs.setTitle(rs.getString(4));
			bbs.setReponseBBS(rs.getInt(5));
			bbs.setTime(rs.getTimestamp(6));
			bbs.setBbs_id(rs.getInt(7));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
	@Override
	public List<BBS> findById(int id) throws Exception {
		String sql ="select * from BBS where id = ?";
		List<BBS> all = new ArrayList<BBS>();
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		BBS bbs =null;
		while(rs.next()){
			bbs = new BBS();
			bbs.setID(rs.getInt(1));
			bbs.setAuthor(rs.getString(2));
			bbs.setLightBBS(rs.getInt(3));
			bbs.setTitle(rs.getString(4));
			bbs.setReponseBBS(rs.getInt(5));
			bbs.setTime(rs.getTimestamp(6));
			bbs.setBbs_id(rs.getInt(7));
			all.add(bbs);
		}
		this.pstmt.close();
		return all;
	}
}
