package com.ssoyoo.blogExperienceSite.repository;


import com.ssoyoo.blogExperienceSite.entity.CampaignApplicationEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.CampaignApplicationPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignApplicationRepository extends JpaRepository<CampaignApplicationEntity, CampaignApplicationPk> {

    boolean existsByUserIdAndCampaignId(Integer userId, Integer campaignId);

}
