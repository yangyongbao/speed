package org.speed.shopservice.proxy;

import java.util.Stack;

import org.speed.shopservice.model.TraceNode;

import com.google.common.collect.Lists;


public class TraceEnv {
	
	public static final ThreadLocal<String> traceId = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "";
		}
	};

	public static final ThreadLocal<Stack<TraceNode>> stack = new ThreadLocal<Stack<TraceNode>>() {
		@Override
		protected Stack<TraceNode> initialValue() {
			return new Stack<TraceNode>();
		}
	};
	
	public static final ThreadLocal<TraceNode> root = new ThreadLocal<TraceNode>() {
		@Override
		protected TraceNode initialValue() {
			TraceNode temp = new TraceNode();
			temp.setTraceId(traceId.get());
			temp.setId(0);
			temp.setName("root");
			temp.setRootNode(true);
			temp.setInTime(System.currentTimeMillis());
			temp.setChildNodes(Lists.newArrayList());
			return temp;
		}
	};

}
