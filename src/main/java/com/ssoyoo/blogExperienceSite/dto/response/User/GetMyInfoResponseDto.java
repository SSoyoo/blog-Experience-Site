package com.ssoyoo.blogExperienceSite.dto.response.User;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetMyInfoResponseDto extends ResponseDto {

    private int id;
    private String email;
    private String name;
    private String nickname;
    private String blogAddress;
    private String homeAddress;
    private String phoneNumber;
    private String profileImageUrl;

    public GetMyInfoResponseDto(UserEntity userEntity) {

        super("SU","Success");

        this.id = userEntity.getUserId();
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.nickname = userEntity.getNickname();
        this.blogAddress = userEntity.getBlogAddress();
        this.homeAddress = userEntity.getHomeAddress();
        this.phoneNumber= userEntity.getPhoneNumber();
        this.profileImageUrl = userEntity.getProfileImageUrl();

    }
}
