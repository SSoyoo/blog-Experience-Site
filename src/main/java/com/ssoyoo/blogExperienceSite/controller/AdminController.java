package com.ssoyoo.blogExperienceSite.controller;

import com.ssoyoo.blogExperienceSite.dto.request.admin.AdminSignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.admin.UpdateAdminRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdateUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
import com.ssoyoo.blogExperienceSite.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("sign-up")
    public ResponseEntity<ResponseDto> adminSignUp(
            @Valid @RequestBody AdminSignUpRequestDto dto
            ){

        ResponseEntity<ResponseDto> response = adminService.signUp(dto);
        return response;
    }

    @PostMapping("sign-in")
    public ResponseEntity<? super SignInResponseDto> adminSignIn(
            @Valid @RequestBody SignInRequestDto dto
            ){
        ResponseEntity<? super SignInResponseDto> response = adminService.signIn(dto);
        return response;
     }

     @PatchMapping("/update")
     public ResponseEntity<ResponseDto> updateAdmin(
             @Valid @RequestBody UpdateAdminRequestDto dto,
             @AuthenticationPrincipal AdminPrincipal adminPrincipal
             ){
        String email = adminPrincipal.getEmail();
        ResponseEntity<ResponseDto> response = adminService.updateAdmin(email,dto);
        return response;

     }

}
