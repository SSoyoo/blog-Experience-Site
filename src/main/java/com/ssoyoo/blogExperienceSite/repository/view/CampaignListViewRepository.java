package com.ssoyoo.blogExperienceSite.repository.view;

import com.ssoyoo.blogExperienceSite.entity.view.CampaignListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignListViewRepository extends JpaRepository<CampaignListViewEntity,Integer> {

    public List<CampaignListViewEntity> findByOrderByCreatedAtDesc();
    public List<CampaignListViewEntity> findByOrderByApplicationCountDesc();
    public List<CampaignListViewEntity> findByOrderByReviewRegistrationDeadline();
    public List<CampaignListViewEntity> findByCampaignTypeOrderByCreatedAt(String type);
    public List<CampaignListViewEntity> findByCampaignTypeOrderByApplicationCountDesc(String type);
    public List<CampaignListViewEntity> findByCampaignTypeOrderByReviewRegistrationDeadline(String type);

}
