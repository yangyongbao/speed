package org.speed.dubbo.service.impl;

import org.speed.dubbo.service.AuthService;
import org.speed.dubbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

	@Autowired
	AuthService authService;
	
	@Override
	public void order() {
		
		System.out.println("order");


		authService.auth();
	}

}
