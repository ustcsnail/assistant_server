package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import bean.Userbean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=new User();
		user.setTele(request.getParameter("tele"));
		user.setPasswd(request.getParameter("passwd"));
			
		
		Userbean userbean=new Userbean(user);
		
		if(userbean.loginCheck())
		{
			//登录数据格式检验成功
			user=userbean.login();
			System.out.println(user.getTele()+""+user.getPasswd()+""+user.isStatus());
		}
		else
		{
			user.setStatus(false);
			user.setPasswd("输入格式错误");
		}
		 JSONObject obj=new JSONObject();
	
		 try {
			 obj.put("id", user.getId());
			 obj.put("name",user.getName());
			 obj.put("passwd", user.getPasswd());//passwd中存放错误信息。
			 obj.put("tele", user.getTele());
			 obj.put("status", user.isStatus());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		 response.setHeader("Content-type","application/json;charset=UTF-8");
		 PrintWriter writer = response.getWriter();	
		 writer.write(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(  
	                (ServletInputStream) request.getInputStream(), "utf-8"));  
	        StringBuffer sb = new StringBuffer("");  
	        String temp;  
	        while ((temp = br.readLine()) != null) {  
	            sb.append(temp);  
	        }  
	        br.close();  
	        String jsonstr = sb.toString();  
	       
	        String result=null;
	        if (null!=jsonstr&&!"".equals(jsonstr)) {  	           
				try {
					JSONObject object = new JSONObject(jsonstr);					
					String type=object.getString("type");
					switch(type)
					{
					  case "register":
					  {
						  result=register(object);
						  break;
					  }
					  case "modifypasswd":
					  {
						  result=modifyPasswd(object);
						  break;  
					  }
					  case "modifyname":
					  {
						  result=modifyName(object);
						  break; 
					  }
					  default:
					  {
						  result="error";
					  }
					}
					
		    		
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="error";
				}  
	        }
	     
		 response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		 response.setHeader("Content-type","text/html;charset=UTF-8");
		 PrintWriter writer = response.getWriter();	
		 writer.write(result);
		 
	}

	public String modifyName(JSONObject object)
	{
		User user=new User();
		Userbean userbean=null;
		try {
			user.setTele(object.getString("tele").trim());
			user.setName(object.getString("name").trim());
			if(null==user.getName()||"".equals(user.getName())||null==user.getTele()||"".equals(user.getTele()))
			{
				return "输入格式错误";
			}
			
			userbean=new Userbean(user);
			String sql="update user set name=? where tele='"+user.getTele()+"'";
			int err=userbean.update(sql, user.getName());
			if(err==1)
			{
				return "success";
			}
			else
			{
				return "修改失败";
			}
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "服务器错误";
		}
		
	}
	
	public String modifyPasswd(JSONObject object)
	{
		User user=new User();
		Userbean userbean=null;
		try {
			user.setTele(object.getString("tele").trim());
			user.setPasswd(object.getString("passwd").trim());
			String newpasswd=object.getString("newpasswd").trim(); 
			
			userbean=new Userbean(user);
			
			if(userbean.loginCheck())
			{
				//登录数据格式检验成功,进行原密码校验
				user=userbean.login();
				if(user.isStatus())
				{
					String sql="update user set password=? where tele='"+user.getTele()+"'";
					int err=userbean.update(sql, newpasswd);
					if(err==1)
					{
						return "success";
					}
					else
					{
						return "修改失败";
					}
				}
				else
				{
					return "原密码错误";
				}
			}
			else
			{				
				return "输入格式错误";
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "服务器错误";
		}
		
	}
	
	public String register(JSONObject object)
	{
		User user=new User();
		Userbean userbean=null;
		try {
			user.setTele(object.getString("tele").trim());
			user.setPasswd(object.getString("passwd").trim());
			user.setName(object.getString("name").trim()); 
			userbean=new Userbean(user);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userbean=null;
		}
		
		 if(userbean!=null&&userbean.registerCheck())
		 {
			 //注册数据格式检验成功
			 //插入数据库
			 int err=userbean.insert();
			 
			 if(err==1)
			 {
				 return "success";
			 }
			 else
			 {
				 return "error";
			 }
		 }
		 else
		 {
			 return"error";
		 }
		 
		
	}
}
