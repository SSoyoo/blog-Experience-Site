package com.ssoyoo.blogExperienceSite.controller;

import com.ssoyoo.blogExperienceSite.dto.request.campaign.PatchCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.admin.SelectReviewerRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetAppliedUserListResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetOngoingCampaignListResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/campaign")
@RequiredArgsConstructor
public class AdminCampaignController {

    private final CampaignService campaignService;

    @GetMapping("ongoing-list/{sort}")
    public ResponseEntity<? super GetOngoingCampaignListResponseDto> getOngoingList(
            @AuthenticationPrincipal AdminPrincipal adminPrincipal,
            @PathVariable("sort") String sort
    ){

        String adminEmail = adminPrincipal.getEmail();
        ResponseEntity<?super GetOngoingCampaignListResponseDto> response =
                campaignService.getOngoingListAsAdmin(adminEmail,sort);
        return response;

    }

    @GetMapping("/applicant/{campaign-id}")
    ResponseEntity<? super GetAppliedUserListResponseDto> getAppliedUserList(
            @AuthenticationPrincipal AdminPrincipal adminPrincipal,
            @PathVariable("campaign-id") Integer campaignId
    ){

        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity <? super GetAppliedUserListResponseDto> response =
                campaignService.getAppliedUserList(adminEmail,campaignId);

        return response;

    }


    @PostMapping("")
    public ResponseEntity<ResponseDto> postCampaign(
            @Valid @RequestBody PostCampaignRequestDto dto,
            @AuthenticationPrincipal AdminPrincipal adminPrincipal
    ){

        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity<ResponseDto> response = campaignService.postCampaign(adminEmail, dto);
        return response;

    }

    @PostMapping("select-reviewer")
    public ResponseEntity<ResponseDto> selectReviewer(
            @AuthenticationPrincipal AdminPrincipal adminPrincipal,
            @Valid @RequestBody SelectReviewerRequestDto dto

    ){
        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity<ResponseDto> response
                = campaignService.selectReviewer(adminEmail,dto);

        return response;

    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> updateCampaign(
            @AuthenticationPrincipal AdminPrincipal adminPrincipal,
            @Valid @RequestBody PatchCampaignRequestDto dto
            ){

        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity<ResponseDto> response =
                campaignService.updateCampaign(adminEmail, dto);
        return response;

    }




}
