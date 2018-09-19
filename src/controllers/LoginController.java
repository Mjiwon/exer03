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
import javax.servlet.http.HttpSession;

import models.AccountDao;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp"); 
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id = req.getParameter("logid");
		String pass = req.getParameter("logpass");
		
		AccountDao aDao = new AccountDao();
		Map map = new HashMap<>();
		map.put("id", id);
		map.put("pass", pass);
		
		Map b = aDao.logcheck(map);
		System.out.println("°á°ú"+b);
		if(b!=null) {
			session.setAttribute("auth", true);
			session.setAttribute("id", id);
			resp.sendRedirect(req.getContextPath()+"/index.do");
		}else {
			session.setAttribute("err", true);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp"); 
			rd.forward(req, resp);
		}
	}
}
