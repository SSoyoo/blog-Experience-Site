package com.ssoyoo.blogExperienceSite.controller;


import com.ssoyoo.blogExperienceSite.dto.request.review.PostReviewRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.review.GetReviewListResponseDto;
import com.ssoyoo.blogExperienceSite.security.UserPrincipal;
import com.ssoyoo.blogExperienceSite.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> postReview(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody PostReviewRequestDto dto
            ){

        int userId = userPrincipal.getUserId();
        ResponseEntity<ResponseDto> response = reviewService.postReview(userId, dto);
        return response;

    }

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(){

        ResponseEntity<? super GetReviewListResponseDto> response =
                reviewService.getReviewList();

        return response;

    }

}


