package org.speed.macos.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="nacos-producer")
public interface EchoService {

	@GetMapping(value = "/hello")
    public String hello();
	
	
}
