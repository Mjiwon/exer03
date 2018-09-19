package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.IssueDao;

@WebServlet("/issue/trend.do")
public class TrendController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IssueDao iDao = new IssueDao();
		
		List<Map> list = iDao.getissuelist();
		if(list.size()==0) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trend.jsp");
			rd.forward(req, resp);			
		}else {
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trend.jsp");
			rd.forward(req, resp);			
		}
		
	}
}
