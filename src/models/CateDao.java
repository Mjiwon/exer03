package models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


public class CateDao extends MyBatisDao{

	public CateDao() throws IOException {
		super();
	}
	
	public List<Map> selCateCount(){
		SqlSession sql = factory.openSession();
		try {
			List<Map> li = sql.selectList("cates.joinCates");
			System.out.println("catedao 21 li = " + li);
			return li;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> selCate(){
		SqlSession sql = factory.openSession();
		try {
			List<Map> li = sql.selectList("cates.selCate");
			return li;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	
	
}
