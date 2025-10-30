package com.synergy.bokja.controller;

import com.synergy.bokja.auth.JwtTokenProvider;
import com.synergy.bokja.dto.UserSignupRequestDTO;
import com.synergy.bokja.dto.UsersResponseDTO;
import com.synergy.bokja.service.UserService;
import lombok.RequiredArgsConstructor;
import com.synergy.bokja.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequestDTO request) {

        if (userService.isDuplicate(request)) {
            return ResponseEntity.status(409).body(new BaseResponse<>(2001, "이미 가입한 사용자 입니다.", null));
        }

        Long uno = userService.signup(request);
        String token = jwtTokenProvider.createToken(uno);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        UsersResponseDTO responseBody = new UsersResponseDTO(uno);

        BaseResponse<UsersResponseDTO> response =
                new BaseResponse<>(1000, "회원가입 성공", responseBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(response);
    }

}
