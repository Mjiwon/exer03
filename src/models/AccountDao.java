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
			String i = sql.selectOne("account.checkid",a);
			if(i!=null) {
				return false;
			}else {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean logcheck(Map a) {
		SqlSession sql = factory.openSession();
		try {
			List m = sql.selectList("account.logMember",a);
			if(m!=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
