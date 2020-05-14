package org.speed.tradeclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shopservice-provider")
public interface ShopService {

	@RequestMapping(value = "/shopList")
    String shopList();
}
