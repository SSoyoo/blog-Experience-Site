package com.ssoyoo.blogExperienceSite.entity.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity(name = "getOngoingCampaignListView")
@Table(name = "getOngoingCampaignListView")
public class OngoingCampaignListViewEntity {
    @Id
    private int campaignId;
    private String campaignType;
    private String location;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private int applicationCount;
    private int favoriteCount;
    private int recruitsNumber;
    private String recruitmentDeadline;
    private boolean isDoneSelect;
    private String createdAt;


}
