package com.ssoyoo.blogExperienceSite.service;



import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;

public interface UserService {
    
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    
}
