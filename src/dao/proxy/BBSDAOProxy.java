package dao.proxy;

import java.util.List;

import  dao.IBBSDAO;
import  dao.impl.BBSDAOImpl;
import  dbc.DatabaseConnection;
import  vo.BBS;

public class BBSDAOProxy implements IBBSDAO {
	private DatabaseConnection dbc = null;
	private IBBSDAO dao =null;
	public BBSDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new BBSDAOImpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(BBS bbs) throws Exception {
		boolean flag = false;
		try {
				flag = this.dao.doCreate(bbs);
		} catch (Exception e) {
			throw e;
		}
		finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<BBS> findAll(String keyWord) throws Exception {
		List<BBS> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	@Override
     public List<BBS> findById(int id) throws Exception {
		List<BBS> all = null;
		try {
			all= this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
}
