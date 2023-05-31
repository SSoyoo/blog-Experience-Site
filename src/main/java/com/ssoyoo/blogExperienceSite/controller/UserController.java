package com.ssoyoo.blogExperienceSite.controller;

import com.ssoyoo.blogExperienceSite.dto.request.user.*;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
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

    @PatchMapping("/withdrawal")
    public ResponseEntity<ResponseDto> withdrawal(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody DeleteUserRequestDto dto
            ){

        int userId = userPrincipal.getUserId();

        ResponseEntity<ResponseDto> response = userService.deleteUser(userId,dto);

        return response;
        }



    }




