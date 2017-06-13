package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Director;
import bean.Directorbean;

/**
 * Servlet implementation class DirectorServlet
 */
@WebServlet("/DirectorServlet")
public class DirectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String type=request.getParameter("type");
		 
		 Directorbean db=new Directorbean();
		 
		 String result="";
		 
		 
		 if(db.checkType(type))
		 {
			 result=db.query(type);
			
		 }
		 else
		 {
			 //没通过类型检测
			 JSONObject json=new JSONObject();
			 try {
				json.put("result",Director.RESULT_TYPEERR);
				
				result=json.toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			 
		 }
		
		 response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		 response.setHeader("Content-type","application/json;charset=UTF-8");
		 PrintWriter writer = response.getWriter();	
		 writer.write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
