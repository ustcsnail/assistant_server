package dao.proxy;

import java.util.List;

import dao.IBBSDAO;
import dao.IResponseDAO;
import dao.impl.BBSDAOImpl;
import dao.impl.ReponseDAOImpl;
import  dbc.DatabaseConnection;
import  vo.BBS;
import  vo.ResponseBBS;

public class ResponseBBSProxy  implements IResponseDAO{
	private DatabaseConnection dbc = null;
	private IResponseDAO dao =null;
	public ResponseBBSProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new ReponseDAOImpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(ResponseBBS bbs) throws Exception {
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
	public List<ResponseBBS> findAll(int id) throws Exception {
		List<ResponseBBS> all = null;
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
     public List<ResponseBBS> findById(int id) throws Exception {
		List<ResponseBBS> all = null;;
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
