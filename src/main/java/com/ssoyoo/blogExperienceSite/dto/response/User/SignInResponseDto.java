package com.ssoyoo.blogExperienceSite.dto.response.User;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expiredTime;

    public SignInResponseDto(String token){
        super("SU","Success");
        this.token = token;
        this.expiredTime = 3600;
    }
    
}
