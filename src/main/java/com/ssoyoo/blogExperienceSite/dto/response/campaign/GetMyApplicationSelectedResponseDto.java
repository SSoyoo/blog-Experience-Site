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
public class GetMyApplicationSelectedResponseDto extends ResponseDto {

private List<SelectedMyCampaignSummary> responseCampaignList;
    public GetMyApplicationSelectedResponseDto(List<GetMyApplicationViewEntity> campaignList) {

        super("SU", "Success");

        List<SelectedMyCampaignSummary> responseCampaingList = new ArrayList<>();

        for(GetMyApplicationViewEntity myApplicationViewEntity : campaignList){

            SelectedMyCampaignSummary selectedMyCampaignSummary =
                    new SelectedMyCampaignSummary(myApplicationViewEntity);

            responseCampaingList.add(selectedMyCampaignSummary);
        }

        this.responseCampaignList = responseCampaingList;

    }
}

@Getter
@Setter
@NoArgsConstructor
class SelectedMyCampaignSummary{

    private int campaignId;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private String reviewRegistrationDeadline;
    private int reviewCount;


    public SelectedMyCampaignSummary(GetMyApplicationViewEntity myApplicationViewEntity) {

        this.campaignId = myApplicationViewEntity.getCampaignId();
        this.title = myApplicationViewEntity.getTitle();
        this.photoUrl = myApplicationViewEntity.getPhotoUrl();
        this.provisionDetail = myApplicationViewEntity.getProvisionDetail();
        this.reviewRegistrationDeadline = myApplicationViewEntity.getReviewRegistrationDeadline();
        this.reviewCount = myApplicationViewEntity.getReviewCount();

    }
}
