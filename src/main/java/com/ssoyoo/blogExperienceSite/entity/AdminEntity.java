package com.ssoyoo.blogExperienceSite.entity;

import com.ssoyoo.blogExperienceSite.dto.request.admin.AdminSignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name="admin")
@Table(name="admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {
    @Id
    private String adminEmail;
    private String adminPassword;
    private String adminName;
    private String adminPhoneNumber;

    public AdminEntity(AdminSignUpRequestDto dto) {
        this.adminEmail = dto.getAdminEmail();
        this.adminPassword = dto.getAdminPassword();
        this.adminName = dto.getAdminName();
        this.adminPhoneNumber = dto.getAdminPhoneNumber();
    }
}
