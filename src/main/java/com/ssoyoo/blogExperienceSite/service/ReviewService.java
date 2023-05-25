package com.ssoyoo.blogExperienceSite.service;

import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {


    ResponseEntity<ResponseDto> postReview(int userId, PostReviewRequestDto dto);
}
