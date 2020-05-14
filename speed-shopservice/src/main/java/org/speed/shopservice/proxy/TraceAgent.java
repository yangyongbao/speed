package org.speed.shopservice.proxy;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.aopalliance.intercept.MethodInvocation;
import org.speed.shopservice.model.TraceNode;

public class TraceAgent {
	
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public static void stopTrace() {
		
		if(TraceEnv.stack.get().size() == 0 ) {
			PrintTreeConsole.print(TraceEnv.root.get());
			
			TraceEnv.traceId.remove();
			TraceEnv.stack.remove();
			TraceEnv.root.remove();
		}
	}

	public static void outTrace() {
		TraceNode popNode = TraceEnv.stack.get().pop();
		popNode.setOutTime(System.currentTimeMillis());
		updateTreeNode(popNode);
	}


	public static void startTrace(MethodInvocation invocation) {
		//记录调用链
		String clazzName = invocation.getThis().getClass().getName();
		String name = clazzName + "." + invocation.getMethod().getName();
		List<TraceNode> childNodes = TraceEnv.root.get().getChildNodes();
		
		TraceNode newNodel = new TraceNode();
		int id = atomicInteger.incrementAndGet();
		if(childNodes.size() == 0) {
			TraceEnv.traceId.set(TraceGenerotor.newTraceId());
			
			newNodel = new TraceNode();
			newNodel.setTraceId(TraceEnv.traceId.get());
			newNodel.setId(id);
			newNodel.setName(name);
			newNodel.setInTime(System.currentTimeMillis());
			newNodel.setParentId(TraceEnv.root.get().getId());
			newNodel.setLevel(1);
			childNodes.add(newNodel);
			TraceEnv.root.get().setTraceId(TraceEnv.traceId.get());
		}else {
			newNodel = new TraceNode();
			newNodel.setTraceId(TraceEnv.traceId.get());
			newNodel.setId(id);
			newNodel.setName(name);
			newNodel.setInTime(System.currentTimeMillis());
			
			if(TraceEnv.stack.get().size() > 0 ) {
				int lastIndex = TraceEnv.stack.get().size() - 1;
				TraceNode parentNode = TraceEnv.stack.get().get(lastIndex);
				newNodel.setLevel(parentNode.getLevel() + 1);
				newNodel.setParentId(parentNode.getId());
				addTreeNode(newNodel);
			}
		}
		
		TraceEnv.stack.get().push(newNodel);
	}

	
	private static void addTreeNode(TraceNode newNodel) {
		TraceNode rootNode = TraceEnv.root.get();
		TraceNode parentNode = findNode(newNodel, rootNode ,1);
		parentNode.getChildNodes().add(newNodel);
	}
	
	private static void updateTreeNode(TraceNode updateNode) {
		TraceNode rootNode = TraceEnv.root.get();
		TraceNode resultNode = findNode(updateNode, rootNode ,2);
		resultNode.setOutTime(updateNode.getOutTime());
		resultNode.setTime(updateNode.getOutTime() - updateNode.getInTime() );
	}
	
	public static TraceNode findNode(TraceNode targetNode,TraceNode beginQueryNode,int type ) {
		int id = (type == 1 ? targetNode.getParentId() : targetNode.getId()); 
		if(beginQueryNode.getId() == id ) {
			return beginQueryNode;
		}
		
		List<TraceNode> childNodeList = beginQueryNode.getChildNodes();
		if(childNodeList.size() > 0 ) {
			for(TraceNode childNode : childNodeList ) {
				TraceNode findResult = findNode(targetNode,childNode,type);
				if(findResult != null ) {
					return findResult;
				}
				continue;
			}
		}
		
		return null;
	}
	
	public static class PrintTreeConsole {
		
		static void print(TraceNode root) {
			StringBuffer sbf = new StringBuffer();
			print(root,sbf);
			System.out.println(sbf.toString());
		}
		
		static void print(TraceNode node,StringBuffer result) {
			if(node.isRootNode()) {
				TraceNode topNode = node.getChildNodes().get(0);
				result = result.append("+root[" + node.getTraceId() + "],total time=").append(topNode.getTime() + "ms").append("\n");
			}
			
			List<TraceNode> childNodes = node.getChildNodes();
			if(childNodes.size() > 0 ) {
				for(TraceNode traceNode : childNodes ) {
					result.append(logChar(traceNode.getLevel())).append(traceNode.getName()).append(",time=" + traceNode.getTime()).append("ms\n");
					print(traceNode, result);
				}
			}			

		}
		
		static String logChar(int level) {
			StringBuffer ss = new StringBuffer();
			for(int i = 0 ; i < level ; i ++ ) {
				ss.append("  ");
			}
			ss.append(" |+=");
			return ss.toString();
		}
	}

	static String logZZ(int level) {
		StringBuffer ss = new StringBuffer();
		for(int i = 0 ; i < level ; i ++ ) {
			ss.append("   ");
		}
		
		ss.append("|+=");
		return ss.toString();
	}
	
	public static class TraceGenerotor {
		
		static String newTraceId() {
			String traceId = UUID.randomUUID().toString();
			traceId = traceId.replace("-", "");
			return traceId;
		}
	}
	
	

}
