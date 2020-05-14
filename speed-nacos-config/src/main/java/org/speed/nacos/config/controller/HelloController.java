package org.speed.nacos.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {
	
	@Value("${yunque.url}")
	String url;
	
	@Value("${server.port}")
	String port;
	
	@GetMapping(value = "/hello")
    public String echo() {
		System.out.println(url + ",port=" + port);
        return "echo";
    }

}
