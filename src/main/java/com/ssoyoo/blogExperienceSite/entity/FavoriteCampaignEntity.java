package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.entity.pk.FavoriteCampaignPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "favoriteCampaign")
@Table(name = "favoriteCampaign")
@IdClass(FavoriteCampaignPk.class)
@Getter
@Setter
@NoArgsConstructor
public class FavoriteCampaignEntity {

    @Id
    private int userId;
    @Id
    private int campaignId;

    public FavoriteCampaignEntity(Integer userId, Integer campaignId) {
        this.userId = userId;
        this.campaignId = campaignId;
    }
}
