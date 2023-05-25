package com.ssoyoo.blogExperienceSite.entity.view;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name= "GetMyApplication")
@Table(name= "GetMyApplication")
@Getter
@Setter
@NoArgsConstructor
public class GetMyApplicationViewEntity {

    @Id
    private int campaignId;
    private int userId;
    private String title;
    private String photoUrl;
    private String provisionDetail;
    private int recruitsNumber;
    private String recruitmentDeadline;
    private String reviewRegistrationDeadline;
    private String campaignEndDate;
    private int applicationCount;
    private int reviewCount;
    private boolean selectionStatus;
    private boolean reviewStatus;
}
