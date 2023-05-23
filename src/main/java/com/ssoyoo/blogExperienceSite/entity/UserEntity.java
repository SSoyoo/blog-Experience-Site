package com.ssoyoo.blogExperienceSite.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String blogAddress;
    private String homeAddress;
    private String phoneNumber;
    private boolean personalInfoAgreement;
    private String profileImageUrl;
    private String createdAt;

    public UserEntity(SignUpRequestDto dto) {

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createdTime = simpleDateFormat.format(now);

        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.blogAddress = dto.getBlogAddress();
        this.homeAddress = dto.getHomeAddress();
        this.phoneNumber = dto.getPhoneNumber();
        this.personalInfoAgreement = dto.isPersonalInfoAgreement();
        this.profileImageUrl = dto.getProfileImageUrl();
        this.createdAt = createdTime;

    }

}
