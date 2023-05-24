package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignDetailResponseDto;
import com.ssoyoo.blogExperienceSite.entity.*;
import com.ssoyoo.blogExperienceSite.repository.*;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImplement implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final PhotoRepository photoRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final CampaignApplicationRepository campaignApplicationRepository;


    @Override
    public ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto) {

        List<String>dtoPhotoList = dto.getPhotoUrlList();
        List<PhotoEntity> campaignPhotoList = new ArrayList<>();

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = new CampaignEntity(dto);
            campaignRepository.save(campaignEntity);

            int campaignId = campaignEntity.getCampaignId();

            for(String photoUrl : dtoPhotoList){
                PhotoEntity photoEntity = new PhotoEntity(campaignId, photoUrl);
                campaignPhotoList.add(photoEntity);
            }

            photoRepository.saveAll(campaignPhotoList);





        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> campaignApplication(int userId, CampaignApplicationRequestDto dto) {
        int campaignId = dto.getCampaignId();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return CustomResponse.noExistUser();

            CampaignEntity campaignEntity =
                    campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            boolean isExistApplication =
                    campaignApplicationRepository.existsByUserIdAndCampaignId(userId,campaignId);
            if(isExistApplication) return CustomResponse.existApplication();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String deadline = campaignEntity.getRecruitmentDeadline();

            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, formatter);
            LocalDateTime now = LocalDateTime.now();

            if(now.isAfter(parsedDeadline)) return CustomResponse.applicationPeriodPassed();

            CampaignApplicationEntity campaignApplicationEntity =
                    new CampaignApplicationEntity(userEntity, dto);

            campaignApplicationRepository.save(campaignApplicationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetCampaignDetailResponseDto> getCampaignDetail(Integer userId, Integer campaignId) {

        GetCampaignDetailResponseDto body = null;
        boolean isApplied = false;

        try {

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            if(userId != null)
                isApplied = campaignApplicationRepository.existsByUserIdAndCampaignId(userId,campaignId);

            List<CampaignApplicationEntity> applicaionList =
                    campaignApplicationRepository.findByCampaignId(campaignId);
            int applicationCount = applicaionList.size();

            body = new GetCampaignDetailResponseDto(campaignEntity,isApplied,applicationCount);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
