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

import entity.ResearchEntity;
import entity.Restaurant;
import bean.Restaurantbean;

/**
 * Servlet implementation class BackRestaurant
 */
@WebServlet("/BackRestaurant")
public class BackRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackRestaurant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s=request.getParameter("operate");
		
		if(s==null)//操作为空，执行querylist
		{
			String str=request.getParameter("type");
			if(str!=null&&!"".equals(str))
			{
				int type=Integer.parseInt(str);
				Restaurantbean bean=new Restaurantbean();
				str=bean.query(type);
				
			try {
					JSONObject obj=new JSONObject(str);
					if(obj.getInt("result")==Restaurant.RESULT_SUCCESS)
					{
						//成功获取
						JSONArray array=obj.getJSONArray("array");
						List<Restaurant> list=new ArrayList<Restaurant>();
						Restaurant e=null;
						for(int i=0;i<array.length();i++)
						{
							e=new Restaurant();
							obj=array.getJSONObject(i);
							e.setId(Integer.parseInt(obj.getString("id")));
							e.setResterant_name(obj.getString("name"));
							e.setAddress(obj.getString("address"));
							e.setDistrict(obj.getString("district"));
							e.setLabel(obj.getString("label"));
							e.setLevel(obj.getInt("level"));
							e.setMark(obj.getInt("mark"));
							e.setPrice_avg(obj.getInt("price"));
							list.add(e);
						}
						request.setAttribute("list", list);
						request.getRequestDispatcher("RestaurantMain.jsp").forward(request, response);
					}
					else 
					{					//可能为空
						request.getRequestDispatcher("RestaurantMain.jsp").forward(request, response);
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
				Restaurantbean bean=new Restaurantbean();
				
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
				Restaurantbean bean=new Restaurantbean();
				
				 Restaurant entity=bean.querySingle(id);
				 if(entity!=null)
				 {
					   request.setAttribute("name", entity.getResterant_name());
					   request.setAttribute("id", entity.getId());
					   request.setAttribute("level", entity.getLevel()+"");
					   request.setAttribute("type", entity.getType()+"");
					   request.setAttribute("address", entity.getAddress());
					   request.setAttribute("district", entity.getDistrict());
					   request.setAttribute("label", entity.getLabel());
					   request.setAttribute("mark", entity.getMark()+"");
					   request.setAttribute("price", entity.getPrice_avg()+"");
					   request.getRequestDispatcher("modifyrestaurant.jsp").forward(request, response);
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
		else {
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String name=request.getParameter("name");
		String label=request.getParameter("label");
		String level_str=request.getParameter("level");
		String type_str=request.getParameter("type");
		String district=request.getParameter("district");
		String address=request.getParameter("address");
		String price_str=request.getParameter("price");
		String mark_str=request.getParameter("mark");
		String id_str=request.getParameter("id");
		Restaurantbean bean=new Restaurantbean();
		
		if(bean.textCheck(name)&&bean.textCheck(label)&&bean.textCheck(level_str)&&bean.textCheck(type_str)&&bean.textCheck(district)
				&&bean.textCheck(address)&&bean.textCheck(price_str)&&bean.textCheck(mark_str))
		{
			int type=Integer.parseInt(type_str);
			int level=Integer.parseInt(level_str);
			int mark=Integer.parseInt(mark_str);
			int price=Integer.parseInt(price_str);
			
			Restaurant entity=new Restaurant();
			entity.setAddress(address);
			entity.setDistrict(district);
			entity.setLabel(label);
			entity.setLevel(level);
			entity.setResterant_name(name);
			entity.setType(type);
			entity.setMark(mark);
			entity.setPrice_avg(price);
			
			int err=0;
			if(id_str==null)
			{
				//插入
				err=bean.insert(entity);
			}
			else {
				//更新
				int id=Integer.parseInt(id_str);
				entity.setId(id);
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
