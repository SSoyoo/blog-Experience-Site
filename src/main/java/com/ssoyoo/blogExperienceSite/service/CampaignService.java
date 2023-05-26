package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.UpdateApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.*;
import org.springframework.http.ResponseEntity;

public interface CampaignService {
    ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto);

    ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto);

    ResponseEntity<? super GetCampaignDetailResponseDto> getCampaignDetail(Integer userId, Integer campaignId);

    ResponseEntity<? super GetCampaignListResponseDto> getCampaignList(String type,String listSort);

    ResponseEntity<ResponseDto> postFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> deleteFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> updateApplication(Integer userId, UpdateApplicationRequestDto dto);

    ResponseEntity<ResponseDto> deleteApplication(int userId, int campaignId);

    ResponseEntity<? super GetMyApplicationOngoingResponseDto> getOngoingList(int userId);

    ResponseEntity<? super GetMyApplicationSelectedResponseDto> getSelectedList(int userId, String sort);

    ResponseEntity<? super GetAppliedUserListResponseDto> getAppliedUserList(String adminEmail, Integer campaignId);
}
