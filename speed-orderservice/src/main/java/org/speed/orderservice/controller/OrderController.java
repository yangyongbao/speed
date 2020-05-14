package org.speed.orderservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @RequestMapping(value = "/orderList")
    public String orderList(){
        System.out.println("provice:orderList");
        
        return "orderList";
    }
	
	
}
