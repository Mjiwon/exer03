package models;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class OpinionDao extends MyBatisDao{

	public OpinionDao() throws IOException {
		super();
	}
	
	public int addComent(Map a) {
		SqlSession sql = factory.openSession();
		try {
			int i = sql.insert("opinion.addcoment",a);
			if(i==1) {
				sql.commit();
			}
			return i;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> getSomeByIno (int ino){
		SqlSession sql = factory.openSession();
		try {
			List<Map> list = sql.selectList("opinion.getSomeByIno",ino);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	 
}
