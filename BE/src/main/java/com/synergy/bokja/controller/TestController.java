package com.synergy.bokja.controller;

import com.synergy.bokja.entity.UserEntity;
import com.synergy.bokja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        UserEntity user = userRepository.findByUno(1L);
        return "복자 서버 정상 작동 중 ";
    }
}