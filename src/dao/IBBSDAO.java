package dao;


import java.util.List;

import vo.BBS;

public interface IBBSDAO {
	public boolean doCreate(BBS bbs) throws Exception;
	public List<BBS> findAll(String keyWord) throws Exception;
	public List<BBS> findById(int id) throws Exception;
}
