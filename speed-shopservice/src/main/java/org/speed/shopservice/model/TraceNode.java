package org.speed.shopservice.model;

import java.util.ArrayList;
import java.util.List;

public class TraceNode {
	
	private String traceId;
	
	private int id;
	
	private String name;
	
	private Long inTime;
	
	private Long outTime;

	private Long time;
	
	private int parentId;
	
	private int level;
	
	List<TraceNode> childNodes = new ArrayList<>();
	
	private boolean isRootNode = false;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRootNode() {
		return isRootNode;
	}

	public void setRootNode(boolean isRootNode) {
		this.isRootNode = isRootNode;
	}

	public List<TraceNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<TraceNode> childNodes) {
		this.childNodes = childNodes;
	}

	public Long getInTime() {
		return inTime;
	}

	public void setInTime(Long inTime) {
		this.inTime = inTime;
	}

	public Long getOutTime() {
		return outTime;
	}

	public void setOutTime(Long outTime) {
		this.outTime = outTime;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
