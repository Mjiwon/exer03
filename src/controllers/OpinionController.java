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
		// ino ���� �Ķ���� �Ѱ����� �� �� in�� ��ϵ� opinion�� �̾Ƽ�
		// JSON���� ���亸���� �� ��Ʈ�ѷ��� get�� ����
		System.out.println("doGet���� ��������?");
		
		int ino = Integer.parseInt(req.getParameter("ino"));
		
		OpinionDao odao = new OpinionDao();
		List<Map> map = odao.getSomeByIno(ino);
		HttpSession session = req.getSession();
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println("doGet�� Map > " + json);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getContentType());
		BufferedReader br = req.getReader(); // *�����ʱ� : ����Ʈ�϶��� �۵�
		String line = br.readLine();
		System.out.println(line);
		Gson gson = new Gson();
		Map map =gson.fromJson(line, Map.class); 	// *�����ʱ� : ����� ���� �迭�̸� �迭�� ��ü�� ������.
		
		System.out.println(map);
		HttpSession session = req.getSession();

		Number ino = (Number)session.getAttribute("ino");
		String talker = (String)session.getAttribute("id");		

		map.put("ino", ino);
		map.put("talker", talker);
		
		OpinionDao oDao = new OpinionDao();
		int r = oDao.addComent(map);
		System.out.println(map.toString() +"������"+r);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(r==1) {
			out.println("{\"rst\":true}");
		}else {
			out.println("{\"rst\":false}");
		}
		
		
	}
	

}
