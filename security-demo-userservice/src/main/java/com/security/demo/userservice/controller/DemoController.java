package com.security.demo.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author shenjg
 * @date 2019/03/27
 **/
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("hello")
    public String Hello() {
        return "hello world";
    }

}

