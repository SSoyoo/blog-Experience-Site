package com.ssoyoo.blogExperienceSite.repository;

import com.ssoyoo.blogExperienceSite.entity.PhotoEntity;
import com.ssoyoo.blogExperienceSite.entity.pk.PhotoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, PhotoPk> {
    
}
