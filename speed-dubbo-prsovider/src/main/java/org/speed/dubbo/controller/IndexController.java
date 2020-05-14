package org.speed.dubbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	
	@RequestMapping("/hello")
	public String hello() {
		
		
		return "ok";
	}
}
