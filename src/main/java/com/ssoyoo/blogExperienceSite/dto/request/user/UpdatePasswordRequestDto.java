package com.ssoyoo.blogExperienceSite.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    @NotBlank
    private String currentPassword;
    @NotBlank
    private String passwordToChange;
}
