package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.admin.AdminSignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<ResponseDto> signUp(AdminSignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
