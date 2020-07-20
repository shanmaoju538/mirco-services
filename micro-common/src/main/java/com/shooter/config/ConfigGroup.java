package com.shooter.config;

import com.dangdang.config.service.GeneralConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
@Setter
public class ConfigGroup {

    private GeneralConfigGroup apiUrl;

    private GeneralConfigGroup dataBase;

    @Value( "${zk.address}" )
    private String zkAddress;

    @Value( "${zk.path}" )
    private String zkPath;

    @Value( "${zk.version}" )
    private String zkVersion;

    @PostConstruct
    private void initApiUrl(){
        ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile(zkAddress, zkPath, zkVersion);
        apiUrl = new ZookeeperConfigGroup(configProfile, "apiurl");
    }

    @PostConstruct
    private void initDataBase(){
        ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile(zkAddress, zkPath, zkVersion);
        dataBase = new ZookeeperConfigGroup(configProfile, "database");
    }

}
