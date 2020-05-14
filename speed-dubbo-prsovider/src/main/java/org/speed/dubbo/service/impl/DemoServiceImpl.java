package org.speed.dubbo.service.impl;

import org.speed.dubbo.service.DemoService;
import org.speed.dubbo.service.OrderService;
import org.speed.dubbo.service.UploadService;
import org.speed.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoServiceImpl implements DemoService {

	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UploadService uploadService;
	
	@Override
	public void exec() {
		
		
		System.out.println("exec");
		
		userService.user();//user->pay
		
		orderService.order();//order->auth->upload
		
		uploadService.upload();//upload

	}

}
