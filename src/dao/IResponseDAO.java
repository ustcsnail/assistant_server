package dao;

import java.util.List;
import vo.ResponseBBS;

public interface IResponseDAO {
	public boolean doCreate(ResponseBBS respbbs) throws Exception;
	public List<ResponseBBS> findAll(int id) throws Exception;
	public List<ResponseBBS> findById(int id) throws Exception;
}
