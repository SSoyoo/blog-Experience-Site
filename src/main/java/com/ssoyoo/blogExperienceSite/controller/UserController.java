package com.ssoyoo.blogExperienceSite.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssoyoo.blogExperienceSite.dto.request.user.PostUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @PostMapping("")
    public ResponseEntity<ResponseDto>postUser(
        @Valid @RequestBody PostUserRequestDto dto
        
    ){
        return null;
    } 

}
