package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.AdminEntity;
import com.ssoyoo.blogExperienceSite.entity.CampaignApplicationEntity;
import com.ssoyoo.blogExperienceSite.entity.CampaignEntity;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import com.ssoyoo.blogExperienceSite.repository.AdminRepository;
import com.ssoyoo.blogExperienceSite.repository.CampaignApplicationRepository;
import com.ssoyoo.blogExperienceSite.repository.CampaignRepository;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignServiceImplement implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final CampaignApplicationRepository campaignApplicationRepository;


    @Override
    public ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto) {

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = new CampaignEntity(dto);
            campaignRepository.save(campaignEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return CustomResponse.noExistUser();

            CampaignApplicationEntity campaignApplicationEntity =
                    new CampaignApplicationEntity(userEntity, dto);

            campaignApplicationRepository.save(campaignApplicationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }
}
