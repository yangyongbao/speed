package org.speed.shopservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("userservice-provider")
public interface UserService {

	@RequestMapping(value = "/userList")
    String userList();
	
}
