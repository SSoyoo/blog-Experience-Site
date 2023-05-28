package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.review.GetReviewListResponseDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {


    ResponseEntity<ResponseDto> postReview(int userId, PostReviewRequestDto dto);

    ResponseEntity<? super GetReviewListResponseDto> getReviewList();


    ResponseEntity<? super GetReviewListResponseDto> getMyReview(Integer userId);

    ResponseEntity<? super GetReviewListResponseDto> getCampaignReview(Integer campaignId);
}
