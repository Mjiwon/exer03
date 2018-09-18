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
		
		
		
		
		
		/*if(Math.random()>0.9) {// 이곳에 DB작업을 해야한다.
			HttpSession session = req.getSession();
			session.setAttribute("auth",true);
			
			resp.sendRedirect(req.getContentType()+"/index.do");
		}else {
			req.setAttribute("err",true);		//	*개인필기 :  req에 setAttribute 하는 것은 일회성 컨트롤러에서 view로 연결할 때까지만 유지된다.
			// MVC패턴 구현 시 view 출력 시 사용해야 될 데이터를 설정하는 영역
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}*/
	}
}
