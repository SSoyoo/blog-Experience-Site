package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.CampaignApplicationEntity;
import com.ssoyoo.blogExperienceSite.entity.ReviewEntity;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import com.ssoyoo.blogExperienceSite.repository.CampaignApplicationRepository;
import com.ssoyoo.blogExperienceSite.repository.ReviewRepository;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CampaignApplicationRepository campaignApplicationRepository;
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
}
