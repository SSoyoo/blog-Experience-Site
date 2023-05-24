package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.entity.pk.PhotoPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "photo")
@Table(name = "photo")
@Getter
@Setter
@IdClass(PhotoPk.class)
@NoArgsConstructor
public class PhotoEntity {
    @Id
    private int campaignId;
    @Id
    private String photoUrl;
    private boolean mainPhoto;

    public PhotoEntity(int campaignId, String photoUrl, boolean isMainPhoto) {

        this.campaignId = campaignId;
        this.photoUrl = photoUrl;
        this.mainPhoto = isMainPhoto;
    }
}
