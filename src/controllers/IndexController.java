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

import handler.ContentHandler;
import models.IssueDao;
import models.OpinionDao;


@WebServlet("/index.do")
public class IndexController extends HttpServlet{
	/*
	 * ����� ��û�� ���ؼ� �۵����Ѿ� �� �۾���
	 * 		service(HttpServletRequest , HttpServletResponse arg1) : get�� post ó�� 
	 * 		doGet(HttpServletRequest , HttpServletResponse arg1) : get�� ó���ǰ�
	 * 		doPost(HttpServletRequest , HttpServletResponse arg1) : post�� ó����.
	 * 
	 * 	�� �����߿� �ϳ��� �̿��ؼ� �����صθ� �ȴ�
	 * 		
	 */
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("auth")==null) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/guest.jsp");	// *�����ʱ� : req.getRequestDispatcher(""); �ȿ��� view�� ������ ��������(�̵����Ѿ��� ������)�� �ۼ��ϸ� �ȴ�.
			/*
			 * MVC ���� �� response(����)�� �����ϱ� ���ؼ� 
			 * �����ϴ� jsp������ WebContent �ٷ� �Ʒ����ٴ�
			 * WEB-INF ������ ���踦 �صθ� ���̴�. 
			 * ���� ���� ������ �ɸ��� ��ζ� ��� ����ϴ� ���� ��ȣ�� ����
			 * 
			 */
			rd.forward(req, resp);	// *�����ʱ� : forward�� �ٸ��������� �Ѱܼ� ������ �����µ� ����ϴ� ��(?)
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");	// *�����ʱ� : req.getRequestDispatcher(""); �ȿ��� view�� ������ ��������(�̵����Ѿ��� ������)�� �ۼ��ϸ� �ȴ�.
			String id = (String)session.getAttribute("id");
			
			ContentHandler ch = new ContentHandler();
			
			IssueDao is = new IssueDao();
			List<Map> someRecent = ch.contSubString(is.getSomeRecent());
			List<Map> oneHot = ch.contSubString(is.getOneHot());
			
			OpinionDao od = new OpinionDao();
			List<Map> getSomeByTalker = ch.contSubString(od.getSomeByTalker(id));
			

			
			session.setAttribute("someRecent", someRecent);
			session.setAttribute("someRecentEnd", someRecent.size()-1);
			
			session.setAttribute("oneHot", oneHot);
			session.setAttribute("oneHotEnd", oneHot.size()-1);
			
			session.setAttribute("getSomeByTalker", getSomeByTalker);
			session.setAttribute("getSomeByTalkerEnd", getSomeByTalker.size()-1);
			
			System.out.println("�α��ξ��̴�"+id);
			session.setAttribute("id", id);
			rd.forward(req, resp);
		}
	}
}
