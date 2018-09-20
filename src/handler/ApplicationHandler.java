package handler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener // * 개인필기 : 리스너에 이렇게 설정을 해놓으면 따로 등록안해도 자동으로 된다.
public class ApplicationHandler implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* 마이바티스 사용 시  아래 (14~18)는 필요없음*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}

		ServletContext ctx = sce.getServletContext();
		ctx.setRequestCharacterEncoding("UTF-8");
	}
}
