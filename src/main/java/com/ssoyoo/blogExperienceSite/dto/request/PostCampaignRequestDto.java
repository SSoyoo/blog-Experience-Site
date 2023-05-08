package com.ssoyoo.blogExperienceSite.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PostCampaignRequestDto {

    @NotBlank
    private String campaignType;
    @NotBlank
    private String category;
    @NotBlank
    private String title;
    @NotBlank
    private String provisionDetail;
    @NotBlank
    private String location;
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
    @NotBlank @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String recruitmentStartDate;



}
