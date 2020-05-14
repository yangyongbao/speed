package org.speed.dubbo.service;

import org.speed.dubbo.model.Order;

public interface OrderService {

	public void order(Order order,String traceId);
}
