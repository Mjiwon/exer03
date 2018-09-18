package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;

@WebServlet("/signup_check.do")
public class SignUpCheckControllers extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*		// �� ��Ʈ�ѷ����� ó������� ������ �� ajax�� ������� json�� ������ �ȴٸ�
		resp.setContentType("application.json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(Math.random()>0.5) {
			out.println(true);
		}else {
			out.println(false);
		}*/
		String id = req.getParameter("id");
		AccountDao aDao = new AccountDao();
		resp.setContentType("application.json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		boolean r = aDao.checkId(id);
		out.print(r);
	}
}
