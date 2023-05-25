package com.ssoyoo.blogExperienceSite.dto.request.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {

    @NotNull
    private int campaignId;
    @NotBlank
    private String reviewTitle;
    @NotBlank
    private String reviewAddress;
    @NotNull
    private int campaignEvaluation;
    private String opinion;

}
