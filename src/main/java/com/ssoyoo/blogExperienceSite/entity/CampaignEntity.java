package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.PatchCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "campaign")
@Table(name = "campaign")
@Getter
@Setter
@NoArgsConstructor
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignId;
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
    private String recruitmentDeadline;
    private String reviewerSelectionDate;
    private String reviewRegistrationStartDate;
    private String reviewRegistrationDeadline;
    private String campaignEndDate;
    private boolean isDoneSelect;
    private String createdAt;
    private String updatedAt;

    public CampaignEntity(PostCampaignRequestDto dto) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter managementFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter campaignFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate recruitmentStartDate = LocalDate.parse(dto.getRecruitmentStartDate(), campaignFormatter);
        LocalDate recruitmentDeadline = recruitmentStartDate.plusDays(10);
        LocalDate reviewerSelectionDate = recruitmentStartDate.plusDays(12);
        LocalDate reviewRegistrationStartDate = recruitmentStartDate.plusDays(13);
        LocalDate reviewRegistrationDeadline = recruitmentStartDate.plusDays(21);
        LocalDate campaignEndDate = recruitmentStartDate.plusDays(22);

        String formattedStartDate = recruitmentStartDate.format(campaignFormatter);
        String formattedDeadline = recruitmentDeadline.format(campaignFormatter);
        String formattedSelectionDate = reviewerSelectionDate.format(campaignFormatter);
        String formattedRegistrationStartDate = reviewRegistrationStartDate.format(campaignFormatter);
        String formattedRegistrationDeadline = reviewRegistrationDeadline.format(campaignFormatter);
        String formattedRegistrationEndDate = campaignEndDate.format(campaignFormatter);


        this.campaignType = dto.getCampaignType();
        this.category = dto.getCategory();
        this.title = dto.getTitle();
        this.provisionDetail = dto.getProvisionDetail();
        this.information = dto.getInformation();
        this.location = dto.getLocation();
        this.precaution = dto.getPrecaution();
        this.mission = dto.getMission();
        this.recruitsNumber = dto.getRecruitsNumber();
        this.searchKeyword = dto.getSearchKeyword();
        this.recruitmentStartDate = formattedStartDate;
        this.recruitmentDeadline = formattedDeadline;
        this.reviewerSelectionDate = formattedSelectionDate;
        this.reviewRegistrationStartDate = formattedRegistrationStartDate;
        this.reviewRegistrationDeadline = formattedRegistrationDeadline;
        this.campaignEndDate = formattedRegistrationEndDate;
        this.createdAt = now.format(managementFormatter);

    }

    public CampaignEntity(CampaignEntity campaignEntity, PatchCampaignRequestDto dto) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter managementFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.campaignId = campaignEntity.getCampaignId();
        this.campaignType = campaignEntity.getCampaignType();
        this.category = campaignEntity.getCategory();
        this.title = dto.getTitle();
        this.provisionDetail = dto.getProvisionDetail();
        this.information = dto.getInformation();
        this.location = campaignEntity.getLocation();
        this.precaution = dto.getPrecaution();
        this.mission = dto.getMission();
        this.recruitsNumber = dto.getRecruitsNumber();
        this.searchKeyword = dto.getSearchKeyword();
        this.recruitmentStartDate = campaignEntity.getRecruitmentStartDate();
        this.recruitmentDeadline = campaignEntity.getRecruitmentDeadline();
        this.reviewerSelectionDate = campaignEntity.getReviewerSelectionDate();
        this.reviewRegistrationStartDate = campaignEntity.getReviewRegistrationStartDate();
        this.reviewRegistrationDeadline = campaignEntity.getReviewRegistrationDeadline();
        this.campaignEndDate = campaignEntity.getCampaignEndDate();
        this.createdAt = now.format(managementFormatter);

    }
}
