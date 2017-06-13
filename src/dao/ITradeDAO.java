package dao;

import java.util.List;

import vo.Trade;


public interface ITradeDAO {
	public boolean doCreate(Trade bbs) throws Exception;
	public List<Trade> findAll(String keyWord) throws Exception;
	public List<Trade> findById(int id) throws Exception;
}

