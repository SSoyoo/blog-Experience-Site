package com.ssoyoo.blogExperienceSite.service;

import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.request.user.PostUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;

public interface UserService {
    
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);
}
