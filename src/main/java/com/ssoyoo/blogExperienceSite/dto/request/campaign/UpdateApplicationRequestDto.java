package com.ssoyoo.blogExperienceSite.dto.request.campaign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateApplicationRequestDto {

    @NotNull
    private int campaignId;
    @NotBlank
    private String userComment;
}
