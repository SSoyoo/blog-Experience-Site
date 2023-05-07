package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {

    public boolean existsByAdminEmail(String adminEmail);
    public boolean existsByAdminPhoneNumber(String adminPhoneNumber);
    public AdminEntity findByAdminEmail(String adminEmail);




}
