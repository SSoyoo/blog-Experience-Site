package com.ssoyoo.blogExperienceSite.dto.request.admin;

import lombok.Data;

@Data
public class AdminSignInRequestDto {

    private String adminEamil;
    private String adminPassword;
    private String adminName;
    private String adminPhoneNumber;
}
