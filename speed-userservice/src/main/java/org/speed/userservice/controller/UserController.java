package org.speed.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/userList")
    public String userList(){
        System.out.println("provice:userList");
        
        return "userList";
    }
	
	@RequestMapping(value = "/users/list")
    public String list(){
        System.out.println("provice:list");
        
        return "list";
    }
	
}
