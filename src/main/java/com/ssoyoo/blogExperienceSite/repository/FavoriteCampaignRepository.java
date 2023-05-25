package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.FavoriteCampaignEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.FavoriteCampaignPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCampaignRepository extends JpaRepository<FavoriteCampaignEntity, FavoriteCampaignPk> {

    public boolean existsByUserIdAndCampaignId(Integer userId, Integer campaignId);
    public FavoriteCampaignEntity findByUserIdAndCampaignId(Integer userId , Integer campaignId);
}
