package com.synergy.bokja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "복자 서버 정상 작동 중";
    }
}