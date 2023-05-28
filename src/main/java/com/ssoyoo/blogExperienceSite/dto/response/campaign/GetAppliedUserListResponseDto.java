package com.ssoyoo.blogExperienceSite.dto.response.campaign;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.GetAppliedUserListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
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
    private String applicationDate;
    private String profileImageUrl;
    private String nickname;
    private String userComment;
    private String blogAddress;


    public AppliedUserSummary(GetAppliedUserListViewEntity appliedUserEntity) {

        this.campaignId = appliedUserEntity.getCampaignId();
        this.userId = appliedUserEntity.getUserId();
        this.applicationDate = appliedUserEntity.getApplicationDate();
        this.profileImageUrl = appliedUserEntity.getProfileImageUrl();
        this.nickname = appliedUserEntity.getNickname();
        this.userComment = appliedUserEntity.getUserComment();
        this.blogAddress = appliedUserEntity.getBlogAddress();

    }
}
