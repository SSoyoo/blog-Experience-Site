package com.ssoyoo.blogExperienceSite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssoyoo.blogExperienceSite.dto.request.user.PostUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
   
    
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto>postUser(
        @Valid @RequestBody PostUserRequestDto dto
        
    ){
        ResponseEntity<ResponseDto> response = userService.signUp(dto);
        return response;
    } 

}
