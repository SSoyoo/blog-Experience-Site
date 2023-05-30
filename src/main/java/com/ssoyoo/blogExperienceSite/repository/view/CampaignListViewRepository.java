package com.ssoyoo.blogExperienceSite.repository.view;

import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignListViewRepository extends JpaRepository<OngoingCampaignListViewEntity,Integer> {

    public List<OngoingCampaignListViewEntity> findByOrderByCreatedAtDesc();
    public List<OngoingCampaignListViewEntity> findByIsDoneSelectOrderByRecruitmentDeadline(boolean status);
    public List<OngoingCampaignListViewEntity> findByOrderByApplicationCountDesc();
    public List<OngoingCampaignListViewEntity> findByOrderByRecruitmentDeadline();
    public List<OngoingCampaignListViewEntity> findByCampaignTypeOrderByCreatedAt(String type);
    public List<OngoingCampaignListViewEntity> findByCampaignTypeOrderByApplicationCountDesc(String type);
    public List<OngoingCampaignListViewEntity> findByCampaignTypeOrderByRecruitmentDeadline(String type);
    public List<OngoingCampaignListViewEntity> findByTitleContaining(String keyword);

}
