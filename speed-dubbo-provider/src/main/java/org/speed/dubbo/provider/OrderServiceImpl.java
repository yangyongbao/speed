package org.speed.dubbo.provider;

import org.speed.dubbo.model.Order;
import org.speed.dubbo.service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

	//@Autowired
	//AuthService authService;
	
	@Override
	public void order(Order order,String traceId) {
		
		System.out.println("OrderService.order:" + order.toString() + ",traceId=" + traceId);

		//authService.auth();
	}

}
