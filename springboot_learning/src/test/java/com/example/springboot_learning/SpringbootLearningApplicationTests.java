package com.example.springboot_learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLearningApplicationTests {

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

}
