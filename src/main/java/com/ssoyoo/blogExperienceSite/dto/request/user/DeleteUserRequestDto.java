package com.ssoyoo.blogExperienceSite.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserRequestDto {
    @NotBlank
    private String password;
}
