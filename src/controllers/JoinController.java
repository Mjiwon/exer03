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
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
		req.setAttribute("id", "");
		req.setAttribute("pass", "");
		req.setAttribute("name", "");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("joinid");
		String pass = req.getParameter("joinpass");
		String pass2 = req.getParameter("joinpass2");
		String name = req.getParameter("joinname");
		String gender = req.getParameter("joingender");
		System.out.println(id+" / "+pass+" / "+name+" / "+gender+" / ");
		
		AccountDao aDao = new AccountDao();
		boolean r = aDao.checkId(id);
		System.out.println(r);
		
		if(name.contains(" ")||!name.matches("[°¡-ÆR]+")||!id.matches("\\w{4,12}")||!pass.matches("\\w{4}")||pass!=pass2){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
			req.setAttribute("id", id);
			req.setAttribute("pass", pass);
			req.setAttribute("name", name);
			rd.forward(req, resp);
		}else {
			if(r) {
				Map map = new HashMap();
				map.put("id", id);
				map.put("pass", pass);
				map.put("name", name);
				map.put("gender", gender);
				
				int i = aDao.addMember(map);
				System.out.println("i = "+i);
				if(i==1) {
					resp.sendRedirect(req.getContextPath()+"/index.do");
					System.out.println("°¡ÀÔ¼º°ø");
				}else{
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
					req.setAttribute("id", id);
					req.setAttribute("pass", pass);
					req.setAttribute("name", name);
					rd.forward(req, resp);
					System.out.println("°¡ÀÔ½ÇÆÐ");
				}
			}
		}
	}
}
