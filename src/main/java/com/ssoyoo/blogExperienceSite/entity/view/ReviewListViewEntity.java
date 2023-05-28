package com.ssoyoo.blogExperienceSite.entity.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "reviewListView")
@Table(name = "reviewListView")
@Getter
@Setter
@NoArgsConstructor
public class ReviewListViewEntity {
    @Id
    private int campaignId;
    private int userId;
    private String title;
    private String reviewTitle;
    private String reviewAddress;
    private int campaignEvaluation;
    private String opinion;
    private String createdAt;
}
