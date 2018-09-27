package controllers;

import java.io.BufferedReader;
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

import com.google.gson.Gson;

import handler.ContentHandler;
import models.IssueDao;
import models.OpinionDao;

@WebServlet("/issue/detail.do")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int no = Integer.parseInt(req.getParameter("no"));
		ContentHandler ch = new ContentHandler();
		
		IssueDao iDao = new IssueDao();
		OpinionDao odao = new OpinionDao();
		List<Map> opinions = odao.getSomeByIno(no);
		List<Map> opinionCount = odao.getCountByChoice(no);
		String cnt ="";
		for(int i = 0; i<opinionCount.size();i++) {
			Map m = opinionCount.get(i);
			if(!m.containsKey("CNT")) {
				cnt = "0";
				m.put("CNT", cnt);
			}else {
				cnt = String.valueOf(m.get("CNT"));
				m.put("CNT", cnt);
			}
		}
		
		Map a = ch.contMapSubString(iDao.getIssuelistDetail(no));

		if(a==null) {
			resp.sendRedirect(req.getContextPath()+"/issue/trend.do");
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
			req.setAttribute("issue", a);
			req.setAttribute("opinions", opinions);
			System.out.println(opinions);
			req.setAttribute("opinionCount", opinionCount);
			rd.forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 아래 내용들은 OpinionController.java로 옮겼다.
		BufferedReader br = req.getReader();	// *개인필기 : 포스트일때만 작동
		String line = br.readLine();
		Gson gson = new Gson();
		Map map = gson.fromJson(line, Map.class);	// *개인필기 : 날라온 글이 배열이면 배열로 객체면 맵으로.
		
		HttpSession session = req.getSession();
		
		Number ino = (Number)session.getAttribute("ino");
		String talker = (String)session.getAttribute("id");		

		map.put("ino", ino);
		map.put("talker", talker);
		
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();

		OpinionDao oDao = new OpinionDao();
		int i = oDao.addComent(map);
		
		
		if(i==1) {
			out.print(true);
		}else {
			out.print(false);
		}
	}


}
