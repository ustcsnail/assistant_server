package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.Collegebean;
import entity.ResearchEntity;

/**
 * Servlet implementation class CollegeServlet
 */
@WebServlet("/CollegeServlet")
public class CollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str=request.getParameter("subject");
		int subject =Integer.parseInt(str);
		Collegebean rb=new Collegebean();
		
		String result="";
		
		 if(rb.checkType(subject))
		 {
			 if(subject==ResearchEntity.TYPE_CONCRETE)
			 {
				 str=request.getParameter("id");
				 result=rb.query(str);
			 }
			 else
			 {
			     result=rb.query(subject);
			 }
			
		 }
		 else
		 {
		
		 JSONObject json=new JSONObject();
		 try {
			json.put("result",ResearchEntity.RESULT_TYPEERR);
			
			result=json.toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
		response.setCharacterEncoding("UTF_8");
		response.setHeader("Content-type","application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();	
		writer.write(result);
		System.out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String str=request.getParameter("subject");
		int subject =Integer.parseInt(str);
		Collegebean rb=new Collegebean();
		if(!rb.checkType(subject))
		{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else
		{
			 String result=rb.query(subject);
			 
			 if(result==null)
			 {
				 request.getRequestDispatcher("error.jsp").forward(request, response);
			 }
			 else
			 {
				 try {
					JSONObject obj=new JSONObject(result);
					if(obj.getInt("result")==ResearchEntity.RESULT_SUCCESS)
					{
						JSONArray array=obj.getJSONArray("array");
						List<ResearchEntity> list=new ArrayList<ResearchEntity>();
						ResearchEntity e=null;
						for(int i=0;i<array.length();i++)
						{
							e=new ResearchEntity();
							obj=array.getJSONObject(i);
							e.setId(obj.getString("id"));
							e.setAuthor(obj.getString("author"));
							e.setTitle(obj.getString("title"));
							e.setDate(obj.getString("date"));
							switch(obj.getString("university"))
							{
							case "xjd":{e.setUniversity("西安交大");break;}
							case "ustc":{e.setUniversity("中国科大");break;}
							case "rmu":{e.setUniversity("中国人大");break;}
							case "nju":{e.setUniversity("南京大学");break;}
							case "seu":{e.setUniversity("东南大学");break;}
							}
							list.add(e);
						}
						request.setAttribute("list", list);
						request.getRequestDispatcher("ResearchMain.jsp").forward(request, response);
						
						
					}
					else
					{
						request.getRequestDispatcher("ResearchMain.jsp").forward(request, response);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			 }
			 
		}
		
		
		
		
		
		
		
		
	}

}
