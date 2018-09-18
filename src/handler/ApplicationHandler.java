package handler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationHandler implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}

		ServletContext ctx = sce.getServletContext();
		ctx.setRequestCharacterEncoding("UTF-8");
	}
}
