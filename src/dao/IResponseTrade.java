package dao;

import java.util.List;

import vo.ResponseTrade;

public interface IResponseTrade {
	public boolean doCreate(ResponseTrade respbbs) throws Exception;
	public List<ResponseTrade> findAll(int id) throws Exception;
	public List<ResponseTrade> findById(int id) throws Exception;
}
