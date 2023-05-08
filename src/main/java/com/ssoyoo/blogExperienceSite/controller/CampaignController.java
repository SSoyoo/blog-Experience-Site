package com.ssoyoo.blogExperienceSite.controller;


import com.ssoyoo.blogExperienceSite.dto.request.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
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
//@RequestMapping("/campaigns")
public class CampaignController {
    //TODO : 컨트롤러 위치 어디가 좋을지 물어보고 수정할 것. 변경필요하면 securityConfig도 수정필요
    private final CampaignService campaignService;

    @PostMapping("admin/campaign")
    public ResponseEntity<ResponseDto> postCampaign(
            @Valid @RequestBody PostCampaignRequestDto dto,
            @AuthenticationPrincipal AdminPrincipal adminPrincipal
            ){

        String adminEmail = adminPrincipal.getEmail();

        ResponseEntity<ResponseDto> response = campaignService.postCampaign(adminEmail, dto);
        return response;


    }
    

}
