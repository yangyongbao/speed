package org.speed.dubbo.provider;

import java.math.BigDecimal;

import org.speed.dubbo.model.Order;
import org.speed.dubbo.service.*;
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
		
		Order order = new Order();
		order.setOrderId("11111");
		order.setPrice(BigDecimal.ZERO);
		orderService.order(order,"111111111111111");//order->auth->upload
		
		uploadService.upload();//upload

	}

}
