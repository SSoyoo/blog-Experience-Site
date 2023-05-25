package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.GetMyApplicationViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetMyApplicationOngoingResponseDto extends ResponseDto {

    private List<OngoingMyCampaignSummary> responseCampaignList;

    public GetMyApplicationOngoingResponseDto(List<GetMyApplicationViewEntity> myApplicationViewEntityList) {

        super("SU" , "Success");

        List<OngoingMyCampaignSummary> responseCampaignList = new ArrayList<>();
        for(GetMyApplicationViewEntity myApplicationViewEntity : myApplicationViewEntityList){
            OngoingMyCampaignSummary ongoingMyCampaignSummary =
                    new OngoingMyCampaignSummary(myApplicationViewEntity);
            responseCampaignList.add(ongoingMyCampaignSummary);
        }

        this.responseCampaignList = responseCampaignList;

    }
}


@Getter
@Setter
@NoArgsConstructor
class OngoingMyCampaignSummary{

    private int campaignId;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private int recruitsNumber;
    private String recruitmentDeadline;
    private int applicationCount;

    public OngoingMyCampaignSummary(GetMyApplicationViewEntity applicationViewEntity){

        this.campaignId = applicationViewEntity.getCampaignId();
        this.title = applicationViewEntity.getTitle();
        this.photoUrl = applicationViewEntity.getPhotoUrl();
        this.provisionDetail = applicationViewEntity.getPhotoUrl();
        this.recruitsNumber = applicationViewEntity.getRecruitsNumber();
        this.recruitmentDeadline = applicationViewEntity.getRecruitmentDeadline();
        this.applicationCount =applicationViewEntity.getApplicationCount();
    }
}


