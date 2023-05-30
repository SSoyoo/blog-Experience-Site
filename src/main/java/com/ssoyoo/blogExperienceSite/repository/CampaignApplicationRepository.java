package com.ssoyoo.blogExperienceSite.repository;


import com.ssoyoo.blogExperienceSite.entity.CampaignApplicationEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.CampaignApplicationPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignApplicationRepository extends JpaRepository<CampaignApplicationEntity, CampaignApplicationPk> {

    public boolean existsByUserIdAndCampaignId(Integer userId, Integer campaignId);
    public boolean existsByCampaignId(Integer campaignId);
    public List<CampaignApplicationEntity> findByCampaignId(Integer campaignId);
    public List<CampaignApplicationEntity> findByUserId(Integer userId);

    public CampaignApplicationEntity findByUserIdAndCampaignId(Integer userId, Integer campaignId);


}
