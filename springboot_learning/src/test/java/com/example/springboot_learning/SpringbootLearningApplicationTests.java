package com.example.springboot_learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
class SpringbootLearningApplicationTests {

//    @Bean
//    public InetAddress getLocalHost()  throws UnknownHostException  {
//        return InetAddress.getLocalHost();
//    }

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SpringbootLearningApplicationTests.class);

    @Test
    void contextLoads() {
    }


    @Test
    public void slf4jDebugTest() {
        logger.debug("+++ 这是一条 slf4j 的 debug 测试信息！");
    }

    @Test
    public void slf4jErrorTest() {
        logger.debug("+++ 这是一条 slf4j 的 error 测试信息！");
    }



    @Test
    public void getLocalIpAddressTest() throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println("+++ 本机ip地址" + hostAddress);
    }

}
