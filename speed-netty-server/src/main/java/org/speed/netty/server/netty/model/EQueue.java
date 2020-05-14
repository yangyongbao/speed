package org.speed.netty.server.netty.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EQueue {
	
	private String queueName;
	private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10000);

	
	public EQueue(String queueName, BlockingQueue<String> blockingQueue) {
		this.queueName = queueName;
		this.blockingQueue = blockingQueue;
	}

	public BlockingQueue<String> getBlockingQueue() {
		return blockingQueue;
	}

	public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	
	
}
