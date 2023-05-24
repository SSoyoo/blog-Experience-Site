package com.ssoyoo.blogExperienceSite.entity.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity(name = "getCampaignList")
@Table(name = "getCampaignList")
public class CampaignListViewEntity {
    @Id
    private int campaignId;
    private String campaignType;
    private String location;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private int applicationCount;
    private int recruitsNumber;
    private String reviewRegistrationDeadline;
    private String createdAt;


}
