package com.ssoyoo.blogExperienceSite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "campaign")
@Table(name = "campaign")
@Getter
@Setter
@NoArgsConstructor
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String campaignType;
    private String category;
    private String title;
    private String provisionDetail;
    private String location;
    private int recruitsNumber;
    //private int applicantsNumber; campaign application에서 count()로 가져오기?
    private String searchKeyword;
    private String information;
    private String mission;
    private String precaution;
    private String recruitmentStartDate;
    private String recruitmentDeadline;
    private String reviewerSelectionDate;
    private String reviewRegistrationStartDate;
    private String reviewRegistrationDeadline;
    private String campaignEndDate;
    //private int favorite_count; favorite 테이블에서 count()?
    private String createdAt;
    private String updatedAt;




}
