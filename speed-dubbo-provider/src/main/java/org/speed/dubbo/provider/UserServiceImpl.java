package org.speed.dubbo.provider;

import org.speed.dubbo.service.*;
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
