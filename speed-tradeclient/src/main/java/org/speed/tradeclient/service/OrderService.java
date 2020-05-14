package org.speed.tradeclient.service;

import org.speed.tradeclient.fallback.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient(name="orderservice-provider",fallback=OrderServiceFallback.class)
public interface OrderService {

	
	
	@RequestMapping(value = "/orderList")
    String orderList();
	
}
