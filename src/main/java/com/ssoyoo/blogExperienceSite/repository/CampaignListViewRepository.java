package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.view.CampaignListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignListViewRepository extends JpaRepository<CampaignListViewEntity,Integer> {

    public List<CampaignListViewEntity> findByOrderByCreatedAtDesc();
    public List<CampaignListViewEntity> findByOrderByApplicationCountDesc();
    public List<CampaignListViewEntity> findByOrderByReviewRegistrationDeadlineDesc();

}
