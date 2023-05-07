package com.ssoyoo.blogExperienceSite.dto.request.admin;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AdminSignUpRequestDto {

    @NotBlank @Email
    private String adminEmail;
    @NotBlank
    private String adminPassword;
    @NotBlank
    private String adminName;
    @NotBlank
    private String adminPhoneNumber;
}
