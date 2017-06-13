package dao.proxy;

import java.util.List;

import vo.ResponseBBS;
import vo.ResponseTrade;
import dao.IResponseDAO;
import dao.IResponseTrade;
import dao.impl.ReponseDAOImpl;
import dao.impl.ResponseTradeImpl;
import dao.impl.EvaluteImpl;
import dbc.DatabaseConnection;

public class EvaluteProxy implements IResponseTrade {
	private DatabaseConnection dbc = null;
	private IResponseTrade dao =null;
	public EvaluteProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new EvaluteImpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(ResponseTrade bbs) throws Exception {
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
	public List<ResponseTrade> findAll(int id) throws Exception {
		List<ResponseTrade> all = null;
		try {
			all = this.dao.findAll(id);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	@Override
     public List<ResponseTrade> findById(int id) throws Exception {
		List<ResponseTrade> all = null;;
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
