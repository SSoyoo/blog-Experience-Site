package com.ssoyoo.blogExperienceSite.service;



import com.ssoyoo.blogExperienceSite.dto.request.user.*;
import com.ssoyoo.blogExperienceSite.dto.response.User.GetMyInfoResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;


public interface UserService {
    
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    public ResponseEntity<ResponseDto> updateUser(Integer userId, UpdateUserRequestDto dto);
    ResponseEntity<ResponseDto> updatePassword(String email, UpdatePasswordRequestDto dto);

    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userEmail);

    ResponseEntity<ResponseDto> deleteUser(int userId, DeleteUserRequestDto dto);
}
