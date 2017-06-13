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





/**
 * Servlet implementation class BBSServlet
 */
@WebServlet("/BBSServlet")
public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private int id;
     private String name;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BBSServlet() {
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
		
		 name =request.getParameter("name");
		switch (id) {
		case 1:
			bbsSelect(request, response);
			break;
		case 2:
			try {
				System.out.println("BBSINSERT");
				bbsInsert(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				bbsSelect(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	public String bbsSelect(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=gb2312");
		System.out.println(id);
		PrintWriter writer= resp.getWriter();
		List<BBS> all=null;
		switch (id) {
		case 1:

			try {
				all = DAOFactory.getIBBSDAOInstance().findAll(name);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 3:

			try {
				int bbs_id=Integer.parseInt(name);
				all = DAOFactory.getIBBSDAOInstance().findById(bbs_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
		
			System.out.println("all not have");
		
		
		
		Iterator<BBS> iterator=all.iterator();
		JSONObject object = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		JSONObject[] jsonObj=new JSONObject[all.size()];
		int i=0;
		System.out.println("JSON implents");
		for(BBS bbs:all){
			//writer.write(bbs.getAuthor());
			try {
				jsonObj[i] =new JSONObject();
				jsonObj[i].put("title",bbs.getTitle() );//向对象里面添加值
		         jsonObj[i].put("author",bbs.getAuthor());
		         jsonObj[i].put("lightBBS",bbs.getLightBBS());
		         jsonObj[i].put("responseBBS",bbs.getReponseBBS());
		         jsonObj[i].put("topic", bbs.getTopic());
		         jsonObj[i].put("bbs_id", bbs.getBbs_id());
		         jsonarray.put(jsonObj[i]);
		         i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(bbs.getAuthor());
		}
		try {
			object.put("bbs", jsonarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.write(object.toString());
		writer.close();
		System.out.println(object.toString());
		System.out.println(id);
		System.out.println(name);
		return null;
	}
	public String bbsInsert(HttpServletResponse reps) throws Exception{
		BBS bbs =null;
		PrintWriter writer=reps.getWriter();
		try {
			bbs =parseJson(name);
			System.out.println(bbs.getAuthor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
		if(DAOFactory.getIBBSDAOInstance().doCreate(bbs))
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
	public BBS parseJson(String jsonString) throws JSONException, UnsupportedEncodingException{
		BBS bbs = new BBS();
	String	jsonresult1= "\"id\":124,\"author\":\"author\",\"title\":\"adange\",\"lightBBS\":0,\"responseBBS\":0}";
	String jsonresult2=null;	
	jsonresult2=new String(jsonString.getBytes("iso8859-1"),"utf-8");
	    System.out.println("parseJson");
		System.out.println(jsonresult2);
		
			JSONObject json =new JSONObject(jsonresult2);
			bbs.setAuthor(json.getString("author"));
			bbs.setID(json.getInt("id"));
			bbs.setLightBBS(0);
			bbs.setReponseBBS(0);
			bbs.setTitle(json.getString("title"));
		
		
		return bbs;
	}

}
