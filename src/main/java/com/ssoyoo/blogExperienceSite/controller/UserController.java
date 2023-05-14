package com.ssoyoo.blogExperienceSite.controller;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdatePasswordRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdateUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.GetMyInfoResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;
import com.ssoyoo.blogExperienceSite.security.UserPrincipal;
import com.ssoyoo.blogExperienceSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto> signUp(
            @Valid @RequestBody SignUpRequestDto dto

    ) {
        ResponseEntity<ResponseDto> response = userService.signUp(dto);
        return response;
    }

    @PostMapping("/signIn")
    public ResponseEntity<? super SignInResponseDto> SignIn(
            @Valid @RequestBody SignInRequestDto dto) {
        ResponseEntity<? super SignInResponseDto> response = userService.signIn(dto);
        return response;
    }

    @GetMapping("/me")
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(
            @AuthenticationPrincipal UserPrincipal userPrincipal

    ){
        String userEmail = userPrincipal.getEmail();
        ResponseEntity<? super GetMyInfoResponseDto> response = userService.getMyInfo(userEmail);
        return response;

    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(
            @Valid @RequestBody UpdateUserRequestDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        {
            String email = userPrincipal.getEmail();
            ResponseEntity<ResponseDto> response = userService.updateUser(email, dto);
            return response;
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<ResponseDto> updatePassword(

            @Valid @RequestBody  UpdatePasswordRequestDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){
            String email = userPrincipal.getEmail();
            ResponseEntity<ResponseDto> response = userService.updatePassword(email,dto);
            return response;
    }




}