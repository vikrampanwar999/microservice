package com.igoujime.ecom.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igoujime.ecom.gateway.redis.RedisService;

@RestController
public class SessionController {
	@Autowired
	RedisService redis;
    @RequestMapping("/")
    public String helloAdmin() {
        return "hello admin";
    }
    
    @RequestMapping("/save")
    public void saveKey(@RequestParam("key")String key,@RequestParam("val")String value ) {
    	redis.saveKey(key, value);
    }
    @RequestMapping("/get")
    public String getValue(@RequestParam("key")String key) {
    	return (String)redis.getValue(key);
    }
}
