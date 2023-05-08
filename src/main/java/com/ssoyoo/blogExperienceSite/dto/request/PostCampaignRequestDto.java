package com.ssoyoo.blogExperienceSite.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class PostCampaignRequestDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String campaignType;
    private String category;
    private String title;
    private String provisionDetail;
    private String location;
    private int recruitsNumber;
    private String searchKeyword;
    private String information;
    private String mission;
    private String precaution;
    private String recruitmentStartDate;



}
