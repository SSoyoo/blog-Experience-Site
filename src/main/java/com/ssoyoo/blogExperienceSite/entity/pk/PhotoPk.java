package com.ssoyoo.blogExperienceSite.entity.pk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class PhotoPk implements Serializable {
    @Column(name = "campaignId")
    private int campaignId;
    @Column(name = "photoUrl")
    private String photoUrl;
}
