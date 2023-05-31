package com.ssoyoo.blogExperienceSite.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String blogAddress;
    @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;
    @NotBlank
    private String homeAddress;
    private String profileImageUrl;
}
