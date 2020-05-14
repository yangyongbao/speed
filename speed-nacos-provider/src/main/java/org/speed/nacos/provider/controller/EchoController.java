package org.speed.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EchoController {

	@GetMapping(value = "/hello")
    public String hello() {
		System.out.println("EchoController#hello");
		
        return "Hello Nacos Discovery";
    }
	
	
}
