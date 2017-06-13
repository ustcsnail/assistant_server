package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Adminbean;
import entity.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username=request.getParameter("username");
		String passwd=request.getParameter("passwd");
		
		if(null==username||"".equals(username)||null==passwd||"".equals(passwd))
		{
			request.getRequestDispatcher("loginerror.html").forward(request, response);
		}
		else
		{
			User user=new User();
			user.setId(username);
			user.setPasswd(passwd);
			
			Adminbean bean=new Adminbean();
			
			if(bean.check(user))
			{
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("loginerror.html").forward(request, response);
			}
		}
	}

}
