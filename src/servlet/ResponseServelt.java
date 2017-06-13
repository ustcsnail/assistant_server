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

import factory.DAOFactory;
import vo.BBS;
import vo.ResponseBBS;

/**
 * Servlet implementation class ResponseServelt
 */
@WebServlet("/ResponseServelt")
public class ResponseServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int id;
    private String name;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServelt() {
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
		List<ResponseBBS> all=null;
		switch (id) {
		case 1:

			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIResponseDAOInstance().findAll(m_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 3:

			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIResponseDAOInstance().findById(m_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
		
		Iterator<ResponseBBS> iterator=all.iterator();
		JSONObject object = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		JSONObject[] jsonObj=new JSONObject[all.size()];
		int i=0;
		System.out.println("JSON implents");
		for(ResponseBBS bbs:all){
			//writer.write(bbs.getAuthor());
			try {
				jsonObj[i] =new JSONObject();
				jsonObj[i].put("title",bbs.getTitle() );//向对象里面添加值
		         jsonObj[i].put("author",bbs.getAuthor());
		         jsonObj[i].put("lightBBS",bbs.getLightBBS());
		         jsonObj[i].put("id",bbs.getBBS_Id());
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
		ResponseBBS repbbs =null;
		PrintWriter writer=response.getWriter();
		try {
			repbbs =parseJson(name);
			System.out.println(repbbs.getAuthor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
		if(DAOFactory.getIResponseDAOInstance().doCreate(repbbs))
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
		return null;
	}
	public ResponseBBS parseJson(String jsonString) throws JSONException, UnsupportedEncodingException{
		ResponseBBS repbbs = new ResponseBBS();
	    String	jsonresult1= "\"id\":124,\"author\":\"author\",\"title\":\"adange\",\"lightBBS\":0,\"responseBBS\":0}";
	     String jsonresult2=null;	 
	     jsonresult2=new String(jsonString.getBytes("iso8859-1"),"utf-8");
	     System.out.println("parseJson");
		 System.out.println(jsonresult2);
		
			JSONObject json =new JSONObject(jsonresult2);
			repbbs.setAuthor(json.getString("author"));
			repbbs.setBBS_id(json.getInt("bbs_id"));
			repbbs.setLightBBS(0);
			repbbs.setId(json.getInt("id"));
			repbbs.setTitle(json.getString("title"));
		
		
		return repbbs;
	}

}
