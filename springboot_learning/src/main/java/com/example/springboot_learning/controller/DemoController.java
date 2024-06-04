package com.example.springboot_learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /**
     * 测试api
     * http://localhost:8080/demo
     * @return
     */
    @RequestMapping("/demo")
    public String demo() {
        return "hello springboot！";
    }
}
