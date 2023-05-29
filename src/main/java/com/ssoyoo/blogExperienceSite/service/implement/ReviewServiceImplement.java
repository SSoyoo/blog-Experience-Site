package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.review.GetReviewListAsAdminResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.review.GetReviewListResponseDto;
import com.ssoyoo.blogExperienceSite.entity.CampaignApplicationEntity;
import com.ssoyoo.blogExperienceSite.entity.ReviewEntity;
import com.ssoyoo.blogExperienceSite.entity.view.ReviewListViewEntity;
import com.ssoyoo.blogExperienceSite.repository.AdminRepository;
import com.ssoyoo.blogExperienceSite.repository.CampaignApplicationRepository;
import com.ssoyoo.blogExperienceSite.repository.ReviewRepository;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.repository.view.ReviewListViewRepository;
import com.ssoyoo.blogExperienceSite.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ReviewRepository reviewRepository;
    private final CampaignApplicationRepository campaignApplicationRepository;
    private final ReviewListViewRepository reviewListViewRepository;
    @Override
    public ResponseEntity<ResponseDto> postReview(int userId, PostReviewRequestDto dto) {

        int campaign = dto.getCampaignId();

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return CustomResponse.noExistUser();

            CampaignApplicationEntity campaignApplicationEntity =
                    campaignApplicationRepository.findByUserIdAndCampaignId(userId,campaign);

            if(campaignApplicationEntity == null) return  CustomResponse.noExistApplication();

            boolean isSelectedCampaign = campaignApplicationEntity.isSelectionStatus();
            if(!isSelectedCampaign) return CustomResponse.noPermission();

            boolean isExistReview = reviewRepository.existsByUserIdAndCampaignId(userId,campaign);
            if(isExistReview) return CustomResponse.existReview();

            ReviewEntity reviewEntity = new ReviewEntity(userId,dto);
            reviewRepository.save(reviewEntity);

            campaignApplicationEntity.setReviewStatus(true);
            campaignApplicationRepository.save(campaignApplicationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList() {

        GetReviewListResponseDto body = null;

        try {
            List<ReviewListViewEntity> reviewList =
                    reviewListViewRepository.findByOrderByCreatedAtDesc();

            body = new GetReviewListResponseDto(reviewList);

        }catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getMyReview(Integer userId) {

        GetReviewListResponseDto body = null;

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return CustomResponse.authenticationFail();

            List<ReviewListViewEntity> reviewList =
                    reviewListViewRepository.findByUserIdOrderByCreatedAt(userId);

            body = new GetReviewListResponseDto(reviewList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getCampaignReview(Integer campaignId) {
        GetReviewListResponseDto body = null;

        try {

            boolean isExistUser = userRepository.existsByUserId(campaignId);
            if(!isExistUser) return CustomResponse.authenticationFail();

            List<ReviewListViewEntity> reviewList =
                    reviewListViewRepository.findByCampaignIdOrderByCreatedAt(campaignId);

            body = new GetReviewListResponseDto(reviewList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetReviewListAsAdminResponseDto> getReviewListAsAdmin(String adminEmail) {

        GetReviewListAsAdminResponseDto body = null;

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFail();

            List<ReviewListViewEntity> reviewList = reviewListViewRepository.findByOrderByCreatedAtDesc();
            body = new GetReviewListAsAdminResponseDto(reviewList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


}
