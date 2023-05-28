package com.ssoyoo.blogExperienceSite.entity.view;

import com.ssoyoo.blogExperienceSite.entity.pk.GetAppliedUserListViewPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "getAppliedUserList")
@Table(name = "getAppliedUserList")
@IdClass(GetAppliedUserListViewPk.class)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetAppliedUserListViewEntity {

    @Id
    private int campaignId;
    @Id
    private int userId;
    private String applicationDate;
    private String profileImageUrl;
    private String nickname;
    private String userComment;
    private String blogAddress;



}
