package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.ReviewEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.ReviewEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, ReviewEntityPk> {

    public boolean existsByUserIdAndCampaignId(Integer userId, Integer campaignId);


}
