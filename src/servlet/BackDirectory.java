package servlet;

import java.io.IOException;
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

import entity.Director;
import entity.Restaurant;
import bean.Directorbean;
import bean.Restaurantbean;

/**
 * Servlet implementation class BackDirectory
 */
@WebServlet("/BackDirectory")
public class BackDirectory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackDirectory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("dgverhger");
		String s=request.getParameter("operate");
		if(s==null)//操作为空，执行querylist
		{
			String type=request.getParameter("type");
			if(type!=null&&!"".equals(type))
			{
				Directorbean bean=new Directorbean();
				String str=bean.query(type);
				
				JSONObject obj;
				try {
					obj = new JSONObject(str);
					if(obj.getInt("result")==Restaurant.RESULT_SUCCESS)
					{
						//成功获取
						JSONArray array=obj.getJSONArray("array");
						List<Director> list=new ArrayList<Director>();
						Director e=null;
						for(int i=0;i<array.length();i++)
						{
							e=new Director();
							obj=array.getJSONObject(i);
							e.setId(obj.getInt("id")+"");
							e.setTitle(obj.getString("title"));
							e.setContent(obj.getString("content"));
							list.add(e);
						}
						request.setAttribute("list", list);
						request.getRequestDispatcher("directorMain.jsp").forward(request, response);
					}
					else 
					{					//可能为空
						request.getRequestDispatcher("directoryMain.jsp").forward(request, response);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.getRequestDispatcher("error.html").forward(request, response);
				}
				
			}
			else
			{			//类型为空
				request.getRequestDispatcher("error.html").forward(request, response);
			}
		}
		else if(s.equals("delete"))
		{
			String str=request.getParameter("id");
			
			if(str!=null&&!"".equals(str))
			{
				int id=Integer.parseInt(str);
				Directorbean bean=new Directorbean();
				
				if(bean.delete(id)==1)
				{
					request.getRequestDispatcher("success.html").forward(request, response);
				}
				else 
				{//失败
					request.getRequestDispatcher("error.html").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("error.html").forward(request, response);
			}
		}
		else if(s.equals("modify"))
		{
            String str=request.getParameter("id");
			
			if(str!=null&&!"".equals(str))
			{
				int id=Integer.parseInt(str);
				Directorbean bean=new Directorbean();
				
				 Director entity=bean.querySingle(id);
				 if(entity!=null)
				 {
					   request.setAttribute("title", entity.getTitle());
					   request.setAttribute("id", entity.getId().trim());
					   request.setAttribute("content", entity.getContent());
					   request.setAttribute("type", entity.getType());
					   request.getRequestDispatcher("modifydirector.jsp").forward(request, response);
				 }
				 else 
				 {
					 request.getRequestDispatcher("error.html").forward(request, response);
				}
			}
		
		    else
		    {
		  	   request.getRequestDispatcher("error.html").forward(request, response);
		    }
		}
		else 
		{
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String id_str=request.getParameter("id");
		
		Directorbean bean=new Directorbean();
		
		if(bean.textCheck(type)&&bean.textCheck(title)&&bean.textCheck(content))
		{
			Director entity=new Director();
			entity.setContent(content);
			entity.setTitle(title);
			entity.setType(type);
			
			int err=0;
			if(id_str==null)
			{
				//插入
				err=bean.insert(entity);
			}
			else {
				//更新
				
				entity.setId(id_str);
				err=bean.update(entity);
			}
			
			if(err==1)
			{
				//成功插入
				request.getRequestDispatcher("success.html").forward(request, response);
			}
			else
			{
				
				request.getRequestDispatcher("error.html").forward(request, response);
				
			}
		}
		else 
		{
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		
	}

}
