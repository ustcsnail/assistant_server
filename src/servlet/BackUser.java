package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import bean.Userbean;

/**
 * Servlet implementation class BackUser
 */
@WebServlet("/BackUser")
public class BackUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operate=request.getParameter("operate");
		if(operate==null)
		{
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		else if(operate.equals("query"))
		{
			User u=new User();
			Userbean bean=new Userbean(u);
			List<User> list=bean.query();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("userlist.jsp").forward(request, response);
		
		}
		else if(operate.equals("delete"))
		{
			User u=new User();
			Userbean bean=new Userbean(u);
			String string=request.getParameter("id");
			int err=0;
			if(string!=null&&!string.equals(""))
			{
				int id=Integer.parseInt(string);
			    err=bean.delete(id);
			}
			
			if(err==1)
			{
				request.getRequestDispatcher("success.html").forward(request, response);
			}
			else {
				request.getRequestDispatcher("error.html").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
