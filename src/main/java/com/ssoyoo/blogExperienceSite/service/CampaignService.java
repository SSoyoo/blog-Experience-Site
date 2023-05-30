package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.*;
import com.ssoyoo.blogExperienceSite.dto.request.admin.SelectReviewerRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.*;
import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import org.springframework.http.ResponseEntity;

public interface CampaignService {
    ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto);

    ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto);

    ResponseEntity<? super GetCampaignDetailResponseDto> getCampaignDetail(Integer userId, Integer campaignId);

    ResponseEntity<? super GetOngoingCampaignListResponseDto> getCampaignList(String type,String listSort);

    ResponseEntity<ResponseDto> postFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> deleteFavorite(Integer userId, Integer campaignId);

    ResponseEntity<ResponseDto> updateApplication(Integer userId, UpdateApplicationRequestDto dto);

    ResponseEntity<ResponseDto> deleteApplication(int userId, int campaignId);

    ResponseEntity<? super GetMyApplicationOngoingResponseDto> getMyOngoingList(int userId);

    ResponseEntity<? super GetMyApplicationSelectedResponseDto> getSelectedList(int userId, String sort);

    ResponseEntity<? super GetAppliedUserListResponseDto> getAppliedUserList(String adminEmail, Integer campaignId);

    ResponseEntity<ResponseDto> selectReviewer(String adminEmail, SelectReviewerRequestDto dto);

    ResponseEntity<? super GetOngoingCampaignListResponseDto> getOngoingListAsAdmin(String adminEmail, String sort);

    ResponseEntity<ResponseDto> updateCampaign(String adminEmail, PatchCampaignRequestDto dto);

    ResponseEntity<? super GetCampaignDetailAsAdminResponseDto> getCampaignDetailAsAdmin(String adminEmail, Integer campaignId);

    ResponseEntity<ResponseDto> deleteCampaign(String adminEmail, DeleteCampaignRequestDto dto);

    ResponseEntity<? super GetOngoingCampaignListResponseDto> searchCampaign(String keyword);
}
