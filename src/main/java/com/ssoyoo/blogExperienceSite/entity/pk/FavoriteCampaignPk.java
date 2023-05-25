package com.ssoyoo.blogExperienceSite.entity.pk;

import javax.persistence.Column;
import java.io.Serializable;

public class FavoriteCampaignPk implements Serializable {
    @Column(name = "userId")
    private int userId;
    @Column(name = "campaignId")
    private int campaignId;
}
