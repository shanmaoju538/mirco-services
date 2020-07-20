package com.shooter.micro.controller;

import com.shooter.config.ConfigGroup;
import com.shooter.micro.remote.HelloRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class HelloController {

    @Resource
    private ConfigGroup configGroup;

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name){
        log.info( "读取到配置URL "+ configGroup.getApiUrl().get( "address_find_url" ) );
        return helloRemote.hello( name );
    }

}
