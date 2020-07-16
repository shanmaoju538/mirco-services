package com.shooter.micro.remote.fallback;

import com.shooter.micro.remote.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteFallBack implements HelloRemote {
    @Override
    public String hello(@RequestParam("name")String name) {
        return "hello " + name + ", i am fallback massage";
    }
}
