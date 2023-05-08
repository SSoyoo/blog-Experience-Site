package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface CampaignService {
    ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto);
}
