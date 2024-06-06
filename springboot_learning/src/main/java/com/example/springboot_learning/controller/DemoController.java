package com.example.springboot_learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试接口", description = "测试接口")
public class DemoController {

    /**
     * 测试api
     * http://localhost:9091/demo
     * @return
     */
    @Operation(summary = "demo-测试接口")
    @RequestMapping("/demo")
    public String demo() {
        return "hello springboot！";
    }
}
