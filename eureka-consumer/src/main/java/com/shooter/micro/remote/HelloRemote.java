package com.shooter.micro.remote;

import com.shooter.micro.remote.fallback.HelloRemoteFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-provider",fallback = HelloRemoteFallBack.class)
@Service
public interface HelloRemote {

    @RequestMapping("hello")
    String hello(@RequestParam("name") String name);
}
