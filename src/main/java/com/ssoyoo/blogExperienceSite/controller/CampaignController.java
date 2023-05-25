package com.ssoyoo.blogExperienceSite.controller;


import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.UpdateApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignDetailResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignListResponseDto;
import com.ssoyoo.blogExperienceSite.repository.CampaignListViewRepository;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
import com.ssoyoo.blogExperienceSite.security.UserPrincipal;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/campaign")

public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> postCampaign(
            @Valid @RequestBody PostCampaignRequestDto dto,
            @AuthenticationPrincipal AdminPrincipal adminPrincipal
            ){

        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity<ResponseDto> response = campaignService.postCampaign(adminEmail, dto);
        return response;

    }
    @PostMapping("/application")
    public ResponseEntity<ResponseDto> campaignApplication(
            @Valid @RequestBody CampaignApplicationRequestDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ){

        int userId = userPrincipal.getUserId();
        ResponseEntity<ResponseDto> response = campaignService.campaignApplication(userId,dto);
        return response;

    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<? super GetCampaignDetailResponseDto> getCampaignDetail(
        @PathVariable Integer campaignId,
        @AuthenticationPrincipal UserPrincipal userPrincipal

    ){
        Integer userId = null;
        if (userPrincipal != null) userId = userPrincipal.getUserId();

        ResponseEntity<? super GetCampaignDetailResponseDto> response =
                campaignService.getCampaignDetail(userId,campaignId);

        return response;
    }

    @GetMapping("/list/{type}/{sort}")
    public ResponseEntity<? super GetCampaignListResponseDto> getCampaignList(
            @PathVariable("type") String type,
            @PathVariable("sort") String listSort

    ){

        ResponseEntity<? super GetCampaignListResponseDto> response =
                campaignService.getCampaignList(type,listSort);

        return response;

    }

    @GetMapping("favorite/{campaignId}")
    public ResponseEntity<ResponseDto> postFavorite(
            @PathVariable("campaignId") Integer campaignId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){

        Integer userId = userPrincipal.getUserId();
        ResponseEntity<ResponseDto> response = campaignService.postFavorite(userId,campaignId);

        return response;

    }

    @GetMapping("application/my")
    public ResponseEntity<? super CampaignListViewRepository> getMyApplicationList(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){

        int userId = userPrincipal.getUserId();
        ResponseEntity<? super CampaignListViewRepository> response =
                campaignService.getMyApplicationList(userId);
        return response;


    }

    @PatchMapping("application")
    public ResponseEntity<ResponseDto> updateApplication(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UpdateApplicationRequestDto dto

            ){
        Integer userId = userPrincipal.getUserId();
        ResponseEntity<ResponseDto> response = campaignService.updateApplication(userId, dto);
        return response;
    }

    @DeleteMapping("favorite/{campaignId}")
    public ResponseEntity<ResponseDto> deleteFavorite(
            @PathVariable("campaignId") Integer campaignId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){

        Integer userId = userPrincipal.getUserId();
        ResponseEntity<ResponseDto> response = campaignService.deleteFavorite(userId,campaignId);

        return response;

    }




}
