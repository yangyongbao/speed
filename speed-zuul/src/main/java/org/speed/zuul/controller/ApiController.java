package org.speed.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	
	@RequestMapping(value = "/api")
    public String api(){
		
		System.out.println("apigateway...");
		
		return "api";
	}
}
