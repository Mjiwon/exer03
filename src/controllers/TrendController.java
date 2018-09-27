package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CateDao;
import models.IssueDao;

@WebServlet("/issue/trend.do")
public class TrendController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		IssueDao iDao = new IssueDao();
		
		List<Map> list = iDao.getissuelist();
		List<Map> catesList = iDao.getCountByCate();
		
		for(int i = 0; i<list.size();i++) {
			Map p = list.get(i);
			String ctr = (String)p.get("CONTENT");
			if(ctr.contains("\n")) {
				p.put("REP", ctr.substring(0,ctr.indexOf("\n")));
			}else {
				p.put("REP", ctr);
			}
			
			String d = sdf.format(p.get("REGDATE"));
			
			p.put("DATE", d);
		}
			
			req.setAttribute("list", list);
			req.setAttribute("catesList", catesList);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trend.jsp");
			rd.forward(req, resp);
	}
}
