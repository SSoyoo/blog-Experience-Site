package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.PhotoEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.PhotoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, PhotoPk> {

    List<PhotoEntity>findByCampaignId(Integer campaignId);
    
}
