package org.speed.tradeclient.controller;

import org.speed.tradeclient.service.OrderService;
import org.speed.tradeclient.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/piss")
    public String piss(){
        
    	String orderList = orderService.orderList();
    	
    	String shopList = shopService.shopList();
    	
    	System.out.println("piss=>>>>orderList=" +  orderList + ",shopList=" + shopList );
    	
        return "ok";
    }
    
    
    @Hystrix
    @HystrixCommand
    @RequestMapping(value = "/hello")
    public String hello(){
    	
    	
    	
    	return "";
    }
    
}
