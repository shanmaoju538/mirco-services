package com.shooter.micro;

import com.shooter.common.json.JsonUtil;
import com.shooter.micro.dao.UserRepository;
import com.shooter.micro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicroDataApplication.class)
@Slf4j
public class DataTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){

        User user = new User();
        user.setUserName( "test" );
        user.setAddress( "测试地址" );
        user.setAvailable( true );
        user.setCreateDate( new Date(  ) );
        userRepository.save( user );

    }

    @Test
    public void testFindByUserName(){
        User user = userRepository.findUserByUserName("test");
        log.info( JsonUtil.convertToString( user ) );
    }

}
