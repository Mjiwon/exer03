package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.*;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		
		
		
		
		/*if(Math.random()>0.9) {// �̰��� DB�۾��� �ؾ��Ѵ�.
			HttpSession session = req.getSession();
			session.setAttribute("auth",true);
			
			resp.sendRedirect(req.getContentType()+"/index.do");
		}else {
			req.setAttribute("err",true);		//	*�����ʱ� :  req�� setAttribute �ϴ� ���� ��ȸ�� ��Ʈ�ѷ����� view�� ������ �������� �����ȴ�.
			// MVC���� ���� �� view ��� �� ����ؾ� �� �����͸� �����ϴ� ����
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}*/
	}
}
