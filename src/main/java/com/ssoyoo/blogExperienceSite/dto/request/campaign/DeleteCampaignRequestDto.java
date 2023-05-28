package com.ssoyoo.blogExperienceSite.dto.request.campaign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteCampaignRequestDto {

    private int campaignId;
    private String password;
}
