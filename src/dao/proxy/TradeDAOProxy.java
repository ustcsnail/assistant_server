package dao.proxy;

import java.util.List;

import vo.BBS;
import vo.Trade;
import dao.IBBSDAO;
import dao.ITradeDAO;
import dao.impl.BBSDAOImpl;
import dao.impl.TradeDAOImpl;
import dbc.DatabaseConnection;

public class TradeDAOProxy implements ITradeDAO{
	private DatabaseConnection dbc = null;
	private ITradeDAO dao =null;
	public TradeDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new TradeDAOImpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(Trade bbs) throws Exception {
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
	public List<Trade> findAll(String keyWord) throws Exception {
		List<Trade> all = null;
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
     public List<Trade> findById(int id) throws Exception {
		List<Trade> all = null;
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
