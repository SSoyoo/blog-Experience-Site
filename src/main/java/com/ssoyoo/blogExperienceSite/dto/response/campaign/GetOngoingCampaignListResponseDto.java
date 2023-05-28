package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetOngoingCampaignListResponseDto extends ResponseDto {

    private List<CampaignSummary> responseCampaignList;

    public GetOngoingCampaignListResponseDto(List<OngoingCampaignListViewEntity> campaignListViewEntityList){

        super("SU","Success");
        List<CampaignSummary> responseCampaignList = new ArrayList<>();

        for(OngoingCampaignListViewEntity campaignListViewEntity : campaignListViewEntityList){

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
    private String type;
    private String location;
    private int applicationCount;
    private int favoriteCount;
    private int recruitsNumber;
    private String recruitmentDeadline;
    private String createdAt;

    public CampaignSummary(OngoingCampaignListViewEntity campaignListViewEntity){
        this.campaignId = campaignListViewEntity.getCampaignId();
        this.title = campaignListViewEntity.getTitle();
        this.type = campaignListViewEntity.getCampaignType();
        this.location = campaignListViewEntity.getLocation();
        this.photoUrl = campaignListViewEntity.getPhotoUrl();
        this.provisionDetail = campaignListViewEntity.getProvisionDetail();
        this.applicationCount = campaignListViewEntity.getApplicationCount();
        this.recruitsNumber = campaignListViewEntity.getRecruitsNumber();
        this.favoriteCount = campaignListViewEntity.getFavoriteCount();
        this.recruitmentDeadline = campaignListViewEntity.getRecruitmentDeadline();
        this.createdAt = campaignListViewEntity.getCreatedAt();
    }

}
