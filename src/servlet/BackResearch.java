package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BackResearchBean;
import entity.ResearchEntity;

/**
 * Servlet implementation class BackResearch
 */
@WebServlet("/BackResearch")
public class BackResearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackResearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String operate=request.getParameter("operate");
		
		BackResearchBean bean=new BackResearchBean();
		if(bean.textCheck(id)&&bean.textCheck(operate))
		{
			int err=0;
			switch(operate)
			{
			   case "delete"://删除
			   {
				   err=bean.delete(id);
				   if(err==1)//成功
					{request.getRequestDispatcher("success.html").forward(request, response);}
					else
					{request.getRequestDispatcher("error.html").forward(request, response);}
				   break;
			   }
			   case "query"://修改
			   {
				   ResearchEntity entity=bean.query(id);
				   if(entity!=null)
				   {
					   request.setAttribute("title", entity.getTitle());
					   request.setAttribute("id", entity.getId().trim());
					   request.setAttribute("author", entity.getAuthor());
					   request.setAttribute("date", entity.getDate());
					   request.setAttribute("content", entity.getContent());
					   request.setAttribute("university", entity.getUniversity());
					   request.setAttribute("subject", entity.getSubject()+"");
					   request.getRequestDispatcher("modifyarticle.jsp").forward(request, response);
				   }
				   else
					{request.getRequestDispatcher("error.html").forward(request, response);}
				   break;
			   }
			}
		
		}
		else {
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String university=request.getParameter("university");
		String id=request.getParameter("id");
		String subject_str=request.getParameter("subject");
		
		String date=request.getParameter("date");
		String content=request.getParameter("content");
		
		BackResearchBean bean=new BackResearchBean();
		
		if(bean.textCheck(title)&&bean.textCheck(author)&&bean.textCheck(university)&&bean.textCheck(subject_str)
				&&bean.textCheck(date)&&bean.textCheck(content))
		{
			ResearchEntity researchEntity=new ResearchEntity();
			researchEntity.setSubject(Integer.parseInt(subject_str));
			researchEntity.setAuthor(author);
			researchEntity.setContent(content);
			researchEntity.setDate(date);
			researchEntity.setTitle(title);
			
			researchEntity.setUniversity(university);
			
			int err=0;
			if(id==null) // 插入
			      err=bean.insert(researchEntity);
			else 
			{
				//更新
				researchEntity.setId(id+"");
				err=bean.update(researchEntity);
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
		else {
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		
		
	
	}

}
