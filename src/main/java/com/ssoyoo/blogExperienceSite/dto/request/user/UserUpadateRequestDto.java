package com.ssoyoo.blogExperienceSite.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpadateRequestDto {
    
    private String nickname;
    private String blogAddress;
    private String homeAddress;
    private String phoneNumber;
    private String profileImageUrl;

}
