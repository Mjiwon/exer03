package handler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener // * �����ʱ� : �����ʿ� �̷��� ������ �س����� ���� ��Ͼ��ص� �ڵ����� �ȴ�.
public class ApplicationHandler implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* ���̹�Ƽ�� ��� ��  �Ʒ� (14~18)�� �ʿ����*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}

		ServletContext ctx = sce.getServletContext();
		ctx.setRequestCharacterEncoding("UTF-8");
	}
}
