package com.ssoyoo.blogExperienceSite.dto.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequestDto {

    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    private String blogAddress;
    private String homeAddress;
    @NotBlank
    @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;
    private String profileImageUrl;
    private boolean personalInfoAgreement;

    
}