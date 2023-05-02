package com.ssoyoo.blogExperienceSite.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String blogAddress;
    private String homeAddress;
    private String phoneNumber;
    private boolean personalInfoAgreement;
    private String profileImageUrl;
    private String createdTime;
    private String updatedTime;
    
}
