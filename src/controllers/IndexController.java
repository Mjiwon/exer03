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
import javax.servlet.http.HttpSession;

import models.IssueDao;


@WebServlet("/index.do")
public class IndexController extends HttpServlet{
	/*
	 * 사용자 요청에 의해서 작동시켜야 될 작업을
	 * 		service(HttpServletRequest , HttpServletResponse arg1) : get과 post 처리 
	 * 		doGet(HttpServletRequest , HttpServletResponse arg1) : get만 처리되고
	 * 		doPost(HttpServletRequest , HttpServletResponse arg1) : post만 처리됨.
	 * 
	 * 	이 세개중에 하나를 이용해서 구현해두면 된다
	 * 		
	 */
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("auth")==null) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/guest.jsp");	// *개인필기 : req.getRequestDispatcher(""); 안에는 view로 설정할 페이지명(이동시켜야할 페이지)을 작성하면 된다.
			/*
			 * MVC 구축 시 response(응답)를 전송하기 위해서 
			 * 설계하는 jsp파일은 WebContent 바로 아래보다는
			 * WEB-INF 안으로 설계를 해두면 편이다. 
			 * 직접 접근 차단이 걸리는 경로라서 뷰로 사용하는 파일 보호를 위해
			 * 
			 */
			rd.forward(req, resp);	// *개인필기 : forward가 다른페이지로 넘겨서 응답을 만들어내는데 사용하는 것(?)
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");	// *개인필기 : req.getRequestDispatcher(""); 안에는 view로 설정할 페이지명(이동시켜야할 페이지)을 작성하면 된다.
			String id = (String)session.getAttribute("id");


			System.out.println("로그인아이다"+id);
			session.setAttribute("id", id);
			rd.forward(req, resp);
		}
	}
}
