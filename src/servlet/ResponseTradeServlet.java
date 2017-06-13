package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.ResponseBBS;
import vo.ResponseTrade;
import factory.DAOFactory;

/**
 * Servlet implementation class ResponseTradeServlet
 */
@WebServlet("/ResponseTradeServlet")
public class ResponseTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int id;
    private String name;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseTradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString=request.getParameter("id");
		if (idString!=null) {
			id=Integer.parseInt(idString);
		}
		System.out.println("id:"+id);
		System.out.println("name+"+name);
		name =request.getParameter("name");
		System.out.println("name:  "+name);
		switch (id) {
		case 1:
			try {
				respbbsSelect(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				System.out.println("BBSINSERT");
				respbbsInsert(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				respbbsSelect(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				respbbsSelect(request,response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case 5:
			try {
				System.out.println("BBSINSERT");
				respbbsInsert(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public String respbbsSelect(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=gb2312");
		PrintWriter writer= response.getWriter();
		int m_id=Integer.parseInt(name);
		List<ResponseTrade> all=null;
		switch (id) {
		case 1:

			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIResponseTradeInstance().findAll(m_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 3:

			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIResponseTradeInstance().findById(m_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 4:
			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIEvaluteDAPInstance().findAll(m_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		default:
			break;
		}
		
		Iterator<ResponseTrade> iterator=all.iterator();
		JSONObject object = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		JSONObject[] jsonObj=new JSONObject[all.size()];
		int i=0;
		System.out.println("JSON implents");
		for(ResponseTrade bbs:all){
			//writer.write(bbs.getAuthor());
			try {
				jsonObj[i] =new JSONObject();
				jsonObj[i].put("title",bbs.getTitle() );
		         jsonObj[i].put("author",bbs.getAuthor());
		         jsonObj[i].put("lightBBS",bbs.getLighttrade());
		         jsonObj[i].put("id",bbs.getTrade_id());
		         jsonObj[i].put("time", bbs.getTime());
		         jsonarray.put(jsonObj[i]);
		         i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(bbs.getAuthor());
		}
		object.put("bbs", jsonarray);
		
		writer.write(object.toString());
		writer.close();
		System.out.println(id);
		System.out.println(name);
		return null;
		
	}
	public String respbbsInsert(HttpServletResponse response) throws Exception{
		ResponseTrade repbbs =null;
		PrintWriter writer=response.getWriter();
		try {
			repbbs =parseJson(name);
			System.out.println(repbbs.getAuthor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (id) {
		case 2:
			try{
				if(DAOFactory.getIResponseTradeInstance().doCreate(repbbs))
				  {
					System.out.println("success");
					writer.write("success");
					writer.flush();
					writer.close();
					return "success";
				  }
				}
				catch(Exception e){
					e.printStackTrace();
				}
			break;
		case 5:
			try{
				System.out.println("prosuccess");
				if(DAOFactory.getIEvaluteDAPInstance().doCreate(repbbs))
				  {
					System.out.println("success");
					writer.write("success");
					writer.flush();
					writer.close();
					return "success";
				  }
				}
				catch(Exception e){
					e.printStackTrace();
				}
			break;
		default:
			break;
		}
		
		return null;
	}
	public ResponseTrade parseJson(String jsonString) throws JSONException, UnsupportedEncodingException{
		ResponseTrade repbbs = new ResponseTrade();
		System.out.println(name);
		System.out.println("jsonString:"+jsonString);
	    String	jsonresult1= "\"id\":124,\"author\":\"author\",\"title\":\"adange\",\"lightBBS\":0,\"responseBBS\":0}";
	     String jsonresult2=null;	 
	     jsonresult2=new String(jsonString.getBytes("iso8859-1"),"utf-8");
	     System.out.println("parseJson");
		 System.out.println(jsonresult2);
		
			JSONObject json =new JSONObject(jsonresult2);
			repbbs.setAuthor(json.getString("author"));
			repbbs.setTrade_id(json.getInt("bbs_id"));
			repbbs.setLighttrade(0);
			repbbs.setId(json.getInt("id"));
			repbbs.setTitle(json.getString("title"));
		
		
		return repbbs;
	}

}
