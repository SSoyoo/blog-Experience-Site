package com.ssoyoo.blogExperienceSite.dto.request.campaign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @NotNull @Size(min = 0, max = 3)
    private List<String> photoUrlList;



}
