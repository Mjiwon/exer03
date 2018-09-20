package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.OpinionDao;

@WebServlet("/issue/opinion.do")
public class OpinionController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ino 값을 파라미터 넘겨줬을 때 그 in로 등록된 opinion들 뽑아서
		// JSON으로 응답보내게 이 컨트롤러의 get을 구현
		System.out.println("doGet으로 들어오나요?");
		
		int ino = Integer.parseInt(req.getParameter("ino"));
		
		OpinionDao odao = new OpinionDao();
		List<Map> map = odao.getSomeByIno(ino);
		HttpSession session = req.getSession();
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println("doGet의 Map > " + json);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getContentType());
		BufferedReader br = req.getReader(); // *개인필기 : 포스트일때만 작동
		String line = br.readLine();
		System.out.println(line);
		Gson gson = new Gson();
		Map map =gson.fromJson(line, Map.class); 	// *개인필기 : 날라온 글이 배열이면 배열로 객체면 맵으로.
		
		System.out.println(map);
		HttpSession session = req.getSession();

		Number ino = (Number)session.getAttribute("ino");
		String talker = (String)session.getAttribute("id");		

		map.put("ino", ino);
		map.put("talker", talker);
		
		OpinionDao oDao = new OpinionDao();
		int r = oDao.addComent(map);
		System.out.println(map.toString() +"▷▷▷"+r);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(r==1) {
			out.println("{\"rst\":true}");
		}else {
			out.println("{\"rst\":false}");
		}
		
		
	}
	

}
