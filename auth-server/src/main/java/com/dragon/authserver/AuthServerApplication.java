package com.dragon.authserver;

import com.dragon.pojo.DragonUser;
import com.dragon.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月10日 13:50
 */
@SpringBootApplication(scanBasePackages = "com.dragon")
@EnableDiscoveryClient
@RestController
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Autowired
    private UserUtils userUtils;

    @GetMapping("index")
    public DragonUser hello() {
        DragonUser user = userUtils.getUser();
        return user;
    }
}
