package com.ssoyoo.blogExperienceSite.service;



import com.ssoyoo.blogExperienceSite.dto.request.user.UpdatePasswordRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdateUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;


public interface UserService {
    
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    public ResponseEntity<ResponseDto> updateUser(String email, UpdateUserRequestDto dto);
    ResponseEntity<ResponseDto> updatePassword(String email, UpdatePasswordRequestDto dto);
}
