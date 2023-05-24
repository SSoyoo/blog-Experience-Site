package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.CampaignApplicationRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.PostCampaignRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignDetailResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.GetCampaignListResponseDto;
import com.ssoyoo.blogExperienceSite.entity.*;
import com.ssoyoo.blogExperienceSite.entity.view.CampaignListViewEntity;
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
    private final CampaignListViewRepository campaignListViewRepository;



    @Override
    public ResponseEntity<ResponseDto> postCampaign(String adminEmail, PostCampaignRequestDto dto) {

        List<String>photos = dto.getPhotoUrlList();
        List<PhotoEntity> campaignPhotoList = new ArrayList<>();

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = new CampaignEntity(dto);
            campaignRepository.save(campaignEntity);

            int campaignId = campaignEntity.getCampaignId();

            for(int i = 0 ; i<photos.size() ; i++){

                String photoUrl = photos.get(i);
                boolean isMainPhoto = (i == 0);
                PhotoEntity photoEntity = new PhotoEntity(campaignId,photoUrl,isMainPhoto);
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

    @Override
    public ResponseEntity<? super GetCampaignListResponseDto> getCampaignList(String type,String listSort) {

        GetCampaignListResponseDto body = null;

        try {
            List<CampaignListViewEntity> campaignList = null;

            if(type.equalsIgnoreCase("all")){

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByOrderByCreatedAtDesc();
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByOrderByApplicationCountDesc();
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByOrderByReviewRegistrationDeadline();
                }
            } else if (type.equalsIgnoreCase("visit")) {

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByCreatedAt("방문형");
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByApplicationCountDesc("방문형");
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByReviewRegistrationDeadline("방문형");
                }
            } else if (type.equalsIgnoreCase("shipping")){

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByCreatedAt("배송형");
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByApplicationCountDesc("배송형");
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByReviewRegistrationDeadline("배송형");
                }

            }

            body = new GetCampaignListResponseDto(campaignList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
