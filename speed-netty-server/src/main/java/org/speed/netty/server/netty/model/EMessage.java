package org.speed.netty.server.netty.model;

import java.util.HashMap;
import java.util.Map;

public class EMessage {
	
	private String id;
	
	private int type;
	
	private String content;
	
	private Long time;
	
	private Map<String, String> ext = new HashMap<String, String>();
	
	public EMessage(String id, int type, String content) {
		this.id = id;
		this.type = type;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getExt() {
		return ext;
	}

	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
	public String deviceId() {
		String deviceId = getExt().get("deviceId");
		return deviceId;
	}
	
	public boolean isExpire() {
		if(System.currentTimeMillis() - getTime() > 1000 * 60 * 5 ) {
			return true;
		}
		
		return false;
	}
	

	
}
