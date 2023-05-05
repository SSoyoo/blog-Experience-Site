package com.ssoyoo.blogExperienceSite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.updateUsereRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
            @Valid @RequestBody SignUpRequestDto dto

    ) {
        ResponseEntity<ResponseDto> response = userService.signUp(dto);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> SignIn(
            @Valid @RequestBody SignInRequestDto dto) {
        ResponseEntity<? super SignInResponseDto> response = userService.signIn(dto);
        return response;
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> userUpdate(
            @RequestHeader("Authorization") String jwt,
            @RequestBody updateUsereRequestDto dto) {

        ResponseEntity<ResponseDto> response = userService.updateUser(jwt, dto);
        return response;

    }

}
