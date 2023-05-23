package com.ssoyoo.blogExperienceSite.controller;


import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
import com.ssoyoo.blogExperienceSite.security.UserPrincipal;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    

}
