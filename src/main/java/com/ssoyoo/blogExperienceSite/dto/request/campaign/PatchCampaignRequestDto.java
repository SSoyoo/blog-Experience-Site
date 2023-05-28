package com.ssoyoo.blogExperienceSite.dto.request.campaign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class PatchCampaignRequestDto {

    @NotNull
    private int campaignId;
    @NotBlank
    private String title;
    @NotBlank
    private String provisionDetail;
    @NotNull
    private int recruitsNumber;
    @NotBlank
    private String searchKeyword;
    @NotBlank
    private String information;
    @NotBlank
    private String mission;
    @NotBlank
    private String precaution;
    @NotBlank
    private String updatedAt;


}
