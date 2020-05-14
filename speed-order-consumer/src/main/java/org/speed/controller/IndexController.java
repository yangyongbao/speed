package org.speed.controller;

import javax.annotation.Resource;

import org.speed.order.consumer.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Resource
	private OrderService orderService;
	
	@RequestMapping("/hello")
	public String hello() {
		
		orderService.order();
		
		
		return "ok";
	}
}

