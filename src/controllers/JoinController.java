package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AccountDao;

@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/views/join.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		
		AccountDao aDao = new AccountDao();
		boolean r = aDao.checkId(id);
		if(name.contains(" ")||!name.matches("[°¡-ÆR]+")||!id.matches("\\w{4,12}")||!pass.matches("\\w{4}")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
			rd.forward(req, resp);
		}else {
			if(r) {
				Map map = new HashMap();
				map.put("id", id);
				map.put("pass", pass);
				map.put("name", name);
				map.put("gender", gender);
				
				int i = aDao.addMember(map);
				if(i==1) {
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
					
					System.out.println("°¡ÀÔ¼º°ø");
				}else{
					System.out.println("°¡ÀÔ½ÇÆÐ");
				}
			}
		}
		
		
		
		
	}
}
