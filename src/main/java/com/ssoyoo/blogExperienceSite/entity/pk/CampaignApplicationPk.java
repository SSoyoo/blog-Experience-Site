package com.ssoyoo.blogExperienceSite.entity.pk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class CampaignApplicationPk implements Serializable {

    @Column(name = "userId")
    private int userId;
    @Column(name = "campaignId")
    private int campaignId;


}
