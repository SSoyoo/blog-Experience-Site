package com.ssoyoo.blogExperienceSite.repository.view;

import com.ssoyoo.blogExperienceSite.entity.view.GetMyApplicationViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetMyApplicationViewRepository extends JpaRepository<GetMyApplicationViewEntity,Integer> {

    public List<GetMyApplicationViewEntity> findByCampaignEndDateGreaterThanAndUserId(String currentDate, Integer userId);
    public List<GetMyApplicationViewEntity> findByCampaignEndDateGreaterThanAndUserIdAndSelectionStatus
            (
                    String currentDate,
                    Integer userId,
                    boolean selectionStatus
            );

    public List<GetMyApplicationViewEntity> findByCampaignEndDateGreaterThanAndUserIdAndReviewStatus(

            String currentDate,
            Integer userId,
            boolean reviewStatus
    );

    public List<GetMyApplicationViewEntity> findByCampaignEndDateLessThanAndUserIdAndReviewStatus(

            String currentDate,
            Integer userId,
            boolean reviewStatus
    );


}
