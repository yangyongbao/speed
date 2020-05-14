package org.speed.tradeclient.fallback;

import org.speed.tradeclient.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements OrderService {

	@Override
	public String orderList() {
		
		System.out.println("OrderServiceFallback->orderList");
		
		return "orderListFallback";
	}

}
