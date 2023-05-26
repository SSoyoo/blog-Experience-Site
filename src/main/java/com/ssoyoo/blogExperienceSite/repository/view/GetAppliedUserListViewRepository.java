package com.ssoyoo.blogExperienceSite.repository.view;

import com.ssoyoo.blogExperienceSite.entity.view.GetAppliedUserListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetAppliedUserListViewRepository extends JpaRepository<GetAppliedUserListViewEntity,Integer> {

    public List<GetAppliedUserListViewEntity> findByCampaignId(Integer campaignId);

}
