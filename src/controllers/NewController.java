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

import models.*;

@WebServlet("/issue/new.do")
public class NewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/new.jsp");	//
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String cate = req.getParameter("cate");
		String content = req.getParameter("content");
		String agree = req.getParameter("agree");
		String disagree = req.getParameter("disagree");
		String writer = (String)session.getAttribute("id");
		
		Map map = new HashMap<>();
			map.put("cate", cate);
			map.put("content", content);
			map.put("agree", agree);
			map.put("disagree", disagree);
			map.put("writer", writer);
			
		Map m = new HashMap<>();
			m.put("cate", cate);

		IssueDao iDao = new IssueDao();
		int i = iDao.addIssue(map);
		System.out.println("���"+i);
		
		if(i == 1) {
			resp.sendRedirect(req.getContextPath()+"/issue/trend.do");
		}else {
			req.setAttribute("fail", true);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/new.jsp"); 
			rd.forward(req, resp);
		}
	}
}
