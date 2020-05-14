package org.speed.shopservice.controller;

import org.speed.shopservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

	@Autowired
    private UserService userService;
	
    @RequestMapping(value = "/shopList")
    public String shopList(){
        System.out.println("provice:shopList");

        userService.userList();
        
        return "shopList";
    }
    
    
}
