package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.CampaignEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GetCampaignDetailAsAdminResponseDto extends ResponseDto {

    private String title;
    private int applicationCount;
    private String campaignType;
    private String category;
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

    public GetCampaignDetailAsAdminResponseDto(
            CampaignEntity campaignEntity,
            int applicationCount

    ) {
        super("SU","Success");
        this.title = campaignEntity.getTitle();
        this.applicationCount = applicationCount;
        this.campaignType = campaignEntity.getCampaignType();
        this.category = campaignEntity.getCategory();
        this.provisionDetail = campaignEntity.getProvisionDetail();
        this.location = campaignEntity.getLocation();
        this.recruitsNumber = campaignEntity.getRecruitsNumber();
        this.searchKeyword = campaignEntity.getSearchKeyword();
        this.information = campaignEntity.getInformation();
        this.mission = campaignEntity.getMission();
        this.precaution = campaignEntity.getPrecaution();
        this.recruitmentStartDate = campaignEntity.getRecruitmentStartDate();
        this.recruitmentDeadline = campaignEntity.getRecruitmentDeadline();
        this.reviewerSelectionDate = campaignEntity.getReviewerSelectionDate();
        this.reviewRegistrationStartDate = campaignEntity.getReviewRegistrationStartDate();
        this.reviewRegistrationDeadline = campaignEntity.getReviewRegistrationDeadline();
        this.campaignEndDate = campaignEntity.getCampaignEndDate();
        this.applicationCount = applicationCount;

    }
}
