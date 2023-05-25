package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.entity.pk.ReviewEntityPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "review")
@Table(name = "review")
@IdClass(ReviewEntityPk.class)
@NoArgsConstructor
@Getter
@Setter
public class ReviewEntity {

    @Id
    private int userId;
    @Id
    private int campaignId;
    private String reviewTitle;
    private String reviewAddress;
    private int campaignEvaluation;
    private String opinion;
    private String createdAt;


    public ReviewEntity(int userId, PostReviewRequestDto dto) {

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createdTime = simpleDateFormat.format(now);

        this.userId = userId;
        this.campaignId = dto.getCampaignId();
        this.reviewTitle = dto.getReviewTitle();
        this.reviewAddress = dto.getReviewAddress();
        this.campaignEvaluation = dto.getCampaignEvaluation();
        this.opinion = dto.getOpinion();
        this.createdAt = createdTime;
    }
}
