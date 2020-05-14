package org.speed.dubbo.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.speed.dubbo.model.Order;
import org.speed.dubbo.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Resource
	private OrderService orderService;
	
	@RequestMapping("/hello")
	public String hello() {
		
		String traceId = "traceId-111111";
		Order order = new Order();
		order.setOrderId("1");
		order.setPrice(BigDecimal.ONE);
		orderService.order(order , traceId);
		
		
		return "ok";
	}
}

