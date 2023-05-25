package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.UpdateApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.entity.pk.CampaignApplicationPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "campaignApplication")
@Table(name = "campaignApplication")
@IdClass(CampaignApplicationPk.class)
@Getter
@Setter
@NoArgsConstructor
public class CampaignApplicationEntity {

    @Id
    private int userId;
    @Id
    private int campaignId;
    private String blogAddress;
    private String userComment;
    private boolean precautionsAgree;
    private boolean selectionStatus;
    private String applicationDate;
    private boolean reviewStatus;


    public CampaignApplicationEntity(
            UserEntity userEntity,
            CampaignApplicationRequestDto dto
    ) {

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createdTime = simpleDateFormat.format(now);

        this.userId = userEntity.getUserId();
        this.campaignId = dto.getCampaignId();
        this.blogAddress = userEntity.getBlogAddress();
        this.userComment = dto.getUserComment();
        this.precautionsAgree = dto.isPrecautionsAgree();
        this.applicationDate = createdTime;

    }


    public CampaignApplicationEntity(
            UserEntity userEntity,
            UpdateApplicationRequestDto dto,
            CampaignApplicationEntity campaignApplicationEntity

    ) {

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updatedTime = simpleDateFormat.format(now);

        this.userId = userEntity.getUserId();
        this.campaignId = dto.getCampaignId();
        this.blogAddress = campaignApplicationEntity.getBlogAddress();
        this.userComment = dto.getUserComment();
        this.precautionsAgree = campaignApplicationEntity.isPrecautionsAgree();
        this.applicationDate = updatedTime;

    }
}
