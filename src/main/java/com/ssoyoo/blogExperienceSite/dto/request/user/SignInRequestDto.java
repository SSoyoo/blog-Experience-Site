package com.ssoyoo.blogExperienceSite.dto.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    
    
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    
}
