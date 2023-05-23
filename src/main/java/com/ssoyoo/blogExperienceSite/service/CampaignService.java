package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface CampaignService {
    ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto);

    ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto);
}
