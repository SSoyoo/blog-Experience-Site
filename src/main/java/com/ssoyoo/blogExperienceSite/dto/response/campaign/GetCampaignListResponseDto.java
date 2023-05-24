package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.CampaignListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetCampaignListResponseDto extends ResponseDto {

    private List<CampaignSummary> responseCampaignList;

    public GetCampaignListResponseDto(List<CampaignListViewEntity> campaignListViewEntityList){

        super("SU","Success");
        List<CampaignSummary> responseCampaignList = new ArrayList<>();

        for(CampaignListViewEntity campaignListViewEntity : campaignListViewEntityList){

            CampaignSummary campaignSummary = new CampaignSummary(campaignListViewEntity);
            responseCampaignList.add(campaignSummary);

        }

        this.responseCampaignList = responseCampaignList;
    }

}

@Getter
@Setter
@NoArgsConstructor
class CampaignSummary{

    private int campaignId;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private int applicationCount;
    private int recruitsNumber;
    private String reviewRegistrationDeadline;
    private String createdAt;

    public CampaignSummary(CampaignListViewEntity campaignListViewEntity){
        this.campaignId = campaignListViewEntity.getCampaignId();
        this.title = campaignListViewEntity.getTitle();
        this.photoUrl = campaignListViewEntity.getPhotoUrl();
        this.provisionDetail = campaignListViewEntity.getProvisionDetail();
        this.applicationCount = campaignListViewEntity.getApplicationCount();
        this.recruitsNumber = campaignListViewEntity.getRecruitsNumber();
        this.reviewRegistrationDeadline = campaignListViewEntity.getReviewRegistrationDeadline();
        this.createdAt = campaignListViewEntity.getCreatedAt();
    }

}
