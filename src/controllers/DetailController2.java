package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.IssueDao;
import models.OpinionDao;


public class DetailController2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int no = Integer.parseInt(req.getParameter("no"));
		IssueDao iDao = new IssueDao();
		OpinionDao odao = new OpinionDao();
		List<Map> opinions = odao.getSomeByIno(no);
		Map a = iDao.getIssuelistDetail(no);

		if(a==null) {
			resp.sendRedirect(req.getContextPath()+"/issue/trend.do");
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
			req.setAttribute("issue", a);
			req.setAttribute("opinions", opinions);
			rd.forward(req, resp);
/*			System.out.println("들어와???");
			resp.setContentType("application.json;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(a);*/
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String choice = req.getParameter("choice");
		String ment = req.getParameter("ment");
		Number ino = (Number)session.getAttribute("ino");
		String talker = (String)session.getAttribute("id");
				
		Map m = new HashMap<>();
		m.put("choice", choice);
		m.put("ment", ment);
		m.put("ino", ino);
		m.put("talker", talker);
		
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();

		OpinionDao oDao = new OpinionDao();
		int i = oDao.addComent(m);
		System.out.println("왜!!!" + choice + "  /  " +ment + " / " + ino + " / " +  talker);

		if(i==1) {
			out.print(true);
		}else {
			out.print(false);
		}
	}
	//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Number no = Integer.parseInt(req.getParameter("no"));
////		String no = req.getParameter("no");
//		
//		System.out.println("넘버"+no);
//		IssueDao iDao = new IssueDao();
//		Map m = iDao.getIssuelistDetail(no);
//		if(m==null) {
//			resp.sendRedirect(req.getContextPath()+"/issue/trend.do");
//		}else {
//			req.setAttribute("m", m);
//			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
//			rd.forward(req, resp);
//		}
//	}

}
