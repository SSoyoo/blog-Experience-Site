package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.GetAppliedUserListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAppliedUserListResponseDto extends ResponseDto {

    private List<AppliedUserSummary> responseAppliedUserList;

    public GetAppliedUserListResponseDto(List<GetAppliedUserListViewEntity> appliedUserEntityList) {
        super("SU", "Success");

        List<AppliedUserSummary> responseAppliedUserList
                = new ArrayList<>();

        for(GetAppliedUserListViewEntity appliedUserEntity : appliedUserEntityList){
            AppliedUserSummary appliedUserSummary = new AppliedUserSummary(appliedUserEntity);
            responseAppliedUserList.add(appliedUserSummary);
        }

        this.responseAppliedUserList = responseAppliedUserList;

    }
}
@Getter
@Setter
@NoArgsConstructor
class AppliedUserSummary{

    private int campaignId;
    private int userId;
    private String title;
    private String provisionDetail;
    private String category;
    private String campaignType;
    private String reviewerSelectionDate;
    private String applicationCount;
    private String userComment;
    boolean isDoneSelect;


    public AppliedUserSummary(GetAppliedUserListViewEntity appliedUserEntity) {
        this.campaignId = appliedUserEntity.getCampaignId();
        this.userId = appliedUserEntity.getUserId();
        this.title = appliedUserEntity.getTitle();
        this.provisionDetail = appliedUserEntity.getProvisionDetail();
        this.category =appliedUserEntity.getCategory();
        this.campaignType = appliedUserEntity.getCampaignType();
        this.reviewerSelectionDate = appliedUserEntity.getReviewerSelectionDate();
        this.applicationCount = appliedUserEntity.getApplicationCount();
        this.userComment = appliedUserEntity.getUserComment();
        this.isDoneSelect = appliedUserEntity.isDoneSelect();

    }
}
