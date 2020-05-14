package org.speed.dubbo.service.impl;

import org.speed.dubbo.service.AuthService;
import org.speed.dubbo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceImpl implements AuthService {

	@Autowired
	UploadService uploadService;
	
	@Override
	public void auth() {
		
		System.out.println("auth");
		
		//uploadService.upload();
		
	}

}
