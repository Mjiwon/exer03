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
	
	public List<Map> getSomeRecent() {
		SqlSession sql = factory.openSession();
		try {
			List<Map> list = sql.selectList("issue.getSomeRecent");
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Map> getOneHot(){
		SqlSession sql = factory.openSession();
		try {
			List<Map> l = sql.selectList("issue.getOneHot");
			return l;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> getAllWithOpinionCount(){
		SqlSession sql = factory.openSession();
		try {
			List<Map> l = sql.selectList("issue.getAllWithOpinionCount");
			return l;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	
	public List<Map> getCountByCate(){
		SqlSession sql = factory.openSession();
		try {
			List<Map> l = sql.selectList("issue.getCountByCate2");
			return l;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	

}
