package com.ssoyoo.blogExperienceSite.dto.request.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateAdminRequestDto{

        @NotBlank
        private String password;
        @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
        private String phoneNumber;
        private String profileImageUrl;
}
