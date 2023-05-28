package com.ssoyoo.blogExperienceSite.repository.view;

import com.ssoyoo.blogExperienceSite.entity.view.ReviewListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewListViewRepository extends JpaRepository<ReviewListViewEntity,Integer> {
   public List<ReviewListViewEntity> findByOrderByCreatedAtDesc();
}
