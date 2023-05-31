package com.ssoyoo.blogExperienceSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssoyoo.blogExperienceSite.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

    public boolean existsByUserId(Integer userId);
    public boolean existsByEmail(String email);
    public boolean existsByNickname(String nickname);
    public boolean existsByBlogAddress(String blogAddress);
    public boolean existsByPhoneNumber(String phoneNumber);
    public boolean existsByNicknameAndUserIdNot(String nickname, Integer userId);
    public boolean existsByPhoneNumberAndUserIdNot(String phoneNumber, Integer userId);
    public boolean existsByBlogAddressAndUserIdNot(String blogAddress, Integer userId);
    public UserEntity findByEmail(String email);
    public UserEntity findByUserId(Integer id);


}
