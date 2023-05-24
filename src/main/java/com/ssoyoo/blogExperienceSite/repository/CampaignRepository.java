package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {

    public CampaignEntity findByCampaignId(Integer id);

}
