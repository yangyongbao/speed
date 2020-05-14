package org.speed.macos.consumer.controller;

import org.speed.macos.consumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EchoController {

	@Autowired
	EchoService echoService;
	
	@GetMapping(value = "/hello")
    public String echo() {
		
		echoService.hello();
		
        return "Hello Nacos Discovery ";
    }
	
	
}
