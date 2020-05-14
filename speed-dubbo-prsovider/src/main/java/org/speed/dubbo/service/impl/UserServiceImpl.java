package org.speed.dubbo.service.impl;

import org.speed.dubbo.service.PayService;
import org.speed.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

	@Autowired
	PayService payService;
	
	@Override
	public void user() {
		
		System.out.println("user");
		
		payService.pay();

	}

}
