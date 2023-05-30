package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.campaign.*;
import com.ssoyoo.blogExperienceSite.dto.request.admin.SelectReviewerRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.campaign.*;
import com.ssoyoo.blogExperienceSite.entity.*;
import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import com.ssoyoo.blogExperienceSite.entity.view.GetAppliedUserListViewEntity;
import com.ssoyoo.blogExperienceSite.entity.view.GetMyApplicationViewEntity;
import com.ssoyoo.blogExperienceSite.entity.view.OngoingCampaignListViewEntity;
import com.ssoyoo.blogExperienceSite.repository.*;
import com.ssoyoo.blogExperienceSite.repository.view.CampaignListViewRepository;
import com.ssoyoo.blogExperienceSite.repository.view.GetAppliedUserListViewRepository;
import com.ssoyoo.blogExperienceSite.repository.view.GetMyApplicationViewRepository;
import com.ssoyoo.blogExperienceSite.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final FavoriteCampaignRepository favoriteCampaignRepository;
    private final GetMyApplicationViewRepository getMyApplicationViewRepository;
    private final GetAppliedUserListViewRepository getAppliedUserListViewRepository;
    private final BCryptPasswordEncoder passwordEncoder;



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
        boolean isFavorite = false;

        try {

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            if(userId != null){
                isApplied = campaignApplicationRepository.existsByUserIdAndCampaignId(userId,campaignId);
                isFavorite = favoriteCampaignRepository.existsByUserIdAndCampaignId(userId,campaignId);
            }

            List<CampaignApplicationEntity> applicaionList =
                    campaignApplicationRepository.findByCampaignId(campaignId);
            int applicationCount = applicaionList.size();

            body = new GetCampaignDetailResponseDto(campaignEntity,isApplied,isFavorite,applicationCount);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetOngoingCampaignListResponseDto> getCampaignList(String type,String listSort) {

        GetOngoingCampaignListResponseDto body = null;

        try {
            List<OngoingCampaignListViewEntity> campaignList = null;

            if(type.equalsIgnoreCase("all")){

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByOrderByCreatedAtDesc();
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByOrderByApplicationCountDesc();
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByOrderByRecruitmentDeadline();
                }
            } else if (type.equalsIgnoreCase("visit")) {

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByCreatedAt("방문형");
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByApplicationCountDesc("방문형");
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByRecruitmentDeadline("방문형");
                }
            } else if (type.equalsIgnoreCase("shipping")){

                if(listSort.equalsIgnoreCase("latest")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByCreatedAt("배송형");
                }else if(listSort.equalsIgnoreCase("popular")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByApplicationCountDesc("배송형");
                }else if(listSort.equalsIgnoreCase("deadline")){
                    campaignList = campaignListViewRepository.findByCampaignTypeOrderByRecruitmentDeadline("배송형");
                }else return CustomResponse.validationFail();

            }

            body = new GetOngoingCampaignListResponseDto(campaignList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> postFavorite(Integer userId, Integer campaignId) {

        try{

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return CustomResponse.authenticationFail();

            boolean isExistCampaign = campaignRepository.existsByCampaignId(campaignId);
            if(!isExistCampaign) return CustomResponse.noExistCampaign();

            boolean isExistFavorite
                    = favoriteCampaignRepository.existsByUserIdAndCampaignId(userId,campaignId);

            if(isExistFavorite) return CustomResponse.existFavorite();

            FavoriteCampaignEntity favoriteCampaignEntity
                    = new FavoriteCampaignEntity(userId,campaignId);

            favoriteCampaignRepository.save(favoriteCampaignEntity);


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteFavorite(Integer userId, Integer campaignId) {

        try{

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return CustomResponse.authenticationFail();

            boolean isExistCampaign = campaignRepository.existsByCampaignId(campaignId);
            if(!isExistCampaign) return CustomResponse.noExistCampaign();

            FavoriteCampaignEntity favoriteCampaignEntity =
                    favoriteCampaignRepository.findByUserIdAndCampaignId(userId,campaignId);

            if(favoriteCampaignEntity == null) return CustomResponse.noExistFavorite();
            favoriteCampaignRepository.delete(favoriteCampaignEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> updateApplication(Integer userId, UpdateApplicationRequestDto dto) {

        int campaignId = dto.getCampaignId();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity ==  null) return CustomResponse.noExistCampaign();

            CampaignApplicationEntity campaignApplicationEntity =
                    campaignApplicationRepository.findByUserIdAndCampaignId(userId,campaignId);

            if(campaignApplicationEntity == null) return CustomResponse.noExistApplication();

            CampaignApplicationEntity updateApplicationEntity =
                    new CampaignApplicationEntity(userEntity,dto,campaignApplicationEntity);

            campaignApplicationRepository.save(updateApplicationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }


    @Override
    public ResponseEntity<ResponseDto> deleteApplication(int userId, int campaignId) {

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return CustomResponse.authenticationFail();

            boolean isExistCampaign = campaignRepository.existsByCampaignId(campaignId);
            if(!isExistCampaign) return CustomResponse.noExistCampaign();

            CampaignApplicationEntity campaignApplicationEntity =
                    campaignApplicationRepository.findByUserIdAndCampaignId(userId,campaignId);

            if(campaignApplicationEntity == null) return CustomResponse.noExistApplication();
            campaignApplicationRepository.delete(campaignApplicationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetMyApplicationOngoingResponseDto> getMyOngoingList(int userId) {

        GetMyApplicationOngoingResponseDto body = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(formatter);

        try {
            List<GetMyApplicationViewEntity> campaignList =
                        getMyApplicationViewRepository
                        .findByCampaignEndDateGreaterThanAndUserId(formattedNow,userId);

            body = new GetMyApplicationOngoingResponseDto(campaignList);


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetMyApplicationSelectedResponseDto> getSelectedList(int userId, String sort) {

        GetMyApplicationSelectedResponseDto body = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(formatter);


        try {

            List<GetMyApplicationViewEntity> campaignList = null;

            if(sort.equalsIgnoreCase("all")){

                campaignList = getMyApplicationViewRepository.
                        findByCampaignEndDateGreaterThanAndUserIdAndSelectionStatus(formattedNow,userId,true);

                body = new GetMyApplicationSelectedResponseDto(campaignList);

                
            } else if(sort.equalsIgnoreCase("posted")){

                campaignList =
                        getMyApplicationViewRepository.
                        findByCampaignEndDateGreaterThanAndUserIdAndReviewStatus(formattedNow,userId,true);

                body = new GetMyApplicationSelectedResponseDto(campaignList);
                
            } else if (sort.equalsIgnoreCase("complete")) {

                campaignList =
                        getMyApplicationViewRepository.
                        findByCampaignEndDateLessThanAndUserIdAndReviewStatus(formattedNow,userId,true);

                body = new GetMyApplicationSelectedResponseDto(campaignList);
                
            } else return CustomResponse.validationFail();


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetAppliedUserListResponseDto> getAppliedUserList(
            String adminEmail,
            Integer campaignId
    ) {

        GetAppliedUserListResponseDto body = null;

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFail();

            List<GetAppliedUserListViewEntity> userList =
                    getAppliedUserListViewRepository.findByCampaignId(campaignId);


            body = new GetAppliedUserListResponseDto(userList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> selectReviewer(String adminEmail, SelectReviewerRequestDto dto) {

        List<Integer> userList = dto.getUserList();
        int appliedCount = userList.size();
        int campaignId = dto.getCampaignId();

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFail();

            if (appliedCount == 0 ) return CustomResponse.doNotSelectUser();

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            boolean isDoneSelect = campaignEntity.isDoneSelect();
            if(isDoneSelect) return CustomResponse.selectionIsDone();

            int recruitNumber = campaignEntity.getRecruitsNumber();
            if(appliedCount > recruitNumber) return CustomResponse.excessOfRecruitment();

            campaignEntity.setDoneSelect(true);
            campaignRepository.save(campaignEntity);

            for(Integer userId : userList){

                CampaignApplicationEntity campaignApplicationEntity =
                        campaignApplicationRepository.findByUserIdAndCampaignId(userId,campaignId);

                if(campaignApplicationEntity == null) return CustomResponse.noExistApplication();

                campaignApplicationEntity.setSelectionStatus(true);
                campaignApplicationRepository.save(campaignApplicationEntity);

            }

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetOngoingCampaignListResponseDto> getOngoingListAsAdmin(String adminEmail, String sort) {

        GetOngoingCampaignListResponseDto body = null;

        try {

            List<OngoingCampaignListViewEntity> campaignList;

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin)return CustomResponse.authenticationFail();

            if(sort.equalsIgnoreCase("all")){
                campaignList = campaignListViewRepository.findByOrderByRecruitmentDeadline();
                body = new GetOngoingCampaignListResponseDto(campaignList);
            } else if(sort.equalsIgnoreCase("not-selected")){
                campaignList = campaignListViewRepository.findByIsDoneSelectOrderByRecruitmentDeadline(false);
                body = new GetOngoingCampaignListResponseDto(campaignList);

            }else return CustomResponse.validationFail();


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> updateCampaign(String adminEmail, PatchCampaignRequestDto dto) {
        int campaignId = dto.getCampaignId();

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            CampaignEntity updateCampaign = new CampaignEntity(campaignEntity,dto);

            campaignRepository.save(updateCampaign);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetCampaignDetailAsAdminResponseDto> getCampaignDetailAsAdmin(
            String adminEmail,
            Integer campaignId
    ) {

        GetCampaignDetailAsAdminResponseDto body = null;

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFail();

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            List<CampaignApplicationEntity> applicationList =
                    campaignApplicationRepository.findByCampaignId(campaignId);

            int applicationCount = applicationList.size();

            body = new GetCampaignDetailAsAdminResponseDto(campaignEntity,applicationCount);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCampaign(String adminEmail, DeleteCampaignRequestDto dto) {

        int campaignId = dto.getCampaignId();
        String password = dto.getPassword();

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) return CustomResponse.authenticationFail();

            String encodedPassword = adminEntity.getAdminPassword();
            boolean isSamePassword = passwordEncoder.matches(password,encodedPassword);

            if(!isSamePassword) return CustomResponse.passwordMisMatch();

            CampaignEntity campaignEntity = campaignRepository.findByCampaignId(campaignId);
            if(campaignEntity == null) return CustomResponse.noExistCampaign();

            boolean isExistApplication = campaignApplicationRepository.existsByCampaignId(campaignId);
            if(!isExistApplication) return CustomResponse.existApplication();

            List<PhotoEntity> photoEntityList = photoRepository.findByCampaignId(campaignId);
            photoRepository.deleteAll(photoEntityList);

            campaignRepository.delete(campaignEntity);


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetOngoingCampaignListResponseDto> searchCampaign(String keyword) {

        GetOngoingCampaignListResponseDto body = null;
        try {

            List<OngoingCampaignListViewEntity> campaignList =
                    campaignListViewRepository.findByTitleContaining(keyword);

            body = new GetOngoingCampaignListResponseDto(campaignList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
