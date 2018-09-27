package handler;

import java.util.List;
import java.util.Map;

public class ContentHandler {
	
	public List<Map> contSubString(List<Map> m) {
		for(int i=0; i<m.size();i++) {
			Map p = m.get(i);
			String ctr = (String)p.get("CONTENT");
			if(ctr.contains("\n")) {
				p.put("REP", ctr.substring(0,ctr.indexOf("\n")));
			}else {
				p.put("REP", ctr);
			}
		}
		return m;
	}
	
	public Map contMapSubString(Map m) {
		for(int i=0; i<m.size();i++) {
			String ctr = (String)m.get("CONTENT");
			if(ctr.contains("\n")) {
				m.put("REP", ctr.substring(0,ctr.indexOf("\n")));
			}else {
				m.put("REP", ctr);
			}
		}
		return m;
	}
}
