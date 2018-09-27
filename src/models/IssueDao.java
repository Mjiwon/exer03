package models;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class IssueDao extends MyBatisDao{

	public IssueDao() throws IOException {
		super();
	}
	
	public int addIssue(Map m) {
		SqlSession sql = factory.openSession();
		try {
			int i = sql.insert("issue.addissue",m);
			if(i==1) {
				sql.commit();
			}
			return i;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> getissuelist (){
		SqlSession sql = factory.openSession();
		try {
			List<Map> list = sql.selectList("issue.getissuelist");
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	public Map getIssuelistDetail(Number no) {
		SqlSession sql = factory.openSession();
		try {
			Map map = sql.selectOne("issue.getissuedetail",no);
			return map;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> yesterIssue() {
		SqlSession sql = factory.openSession();
		try {
			List<Map> list = sql.selectList("issue.yesterIssue");
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
