package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.UpdateApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignDetailResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignListResponseDto;
import com.ssoyoo.blogExperienceSite.repository.CampaignListViewRepository;
import org.springframework.http.ResponseEntity;

public interface CampaignService {
    ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto);

    ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto);

    ResponseEntity<? super GetCampaignDetailResponseDto> getCampaignDetail(Integer userId, Integer campaignId);

    ResponseEntity<? super GetCampaignListResponseDto> getCampaignList(String type,String listSort);

    ResponseEntity<ResponseDto> postFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> deleteFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> updateApplication(Integer userId, UpdateApplicationRequestDto dto);

    ResponseEntity<? super CampaignListViewRepository> getMyApplicationList(int userId);

    ResponseEntity<ResponseDto> deleteApplication(int userId, int campaignId);
}
