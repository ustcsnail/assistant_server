package factory;

import dao.IBBSDAO;
import dao.IResponseDAO;
import dao.IResponseTrade;
import dao.ITradeDAO;
import dao.proxy.BBSDAOProxy;
import dao.proxy.EvaluteProxy;
import dao.proxy.ResponseBBSProxy;
import dao.proxy.ResponseTradeProxy;
import dao.proxy.TradeDAOProxy;

public class DAOFactory {
	public static IBBSDAO getIBBSDAOInstance() throws Exception{
		return new BBSDAOProxy();
	}
	public static IResponseDAO getIResponseDAOInstance() throws Exception{
		return new ResponseBBSProxy();
	}
	public static IResponseTrade getIResponseTradeInstance() throws Exception{
		return new ResponseTradeProxy();
	}
	public static ITradeDAO getITradeDAPInstance() throws Exception{
		return new TradeDAOProxy();
	}
	public static IResponseTrade getIEvaluteDAPInstance() throws Exception{
		return new EvaluteProxy();
	}
}
