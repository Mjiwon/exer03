package models;

import java.io.IOException;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

public class AccountDao extends MyBatisDao{

	public AccountDao() throws IOException {
		super();
	}
	
	public int addMember(Map a) {
		SqlSession sql = factory.openSession();
		try {
			int i = sql.insert("account.addNewMember",a);
			if(i==1) {
				sql.commit();
			}
			return i;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean checkId(String a) {
		SqlSession sql = factory.openSession();
		try {
			String i = sql.selectOne("account.getMemberId",a);
			if(i==null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Map logcheck(Map a) {
		SqlSession sql = factory.openSession();
		try {
			
			Map mp = sql.selectOne("account.logMember",a);

			return mp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
