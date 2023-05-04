package com.ssoyoo.blogExperienceSite.service;

import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;

public interface UserService {
    
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
}
