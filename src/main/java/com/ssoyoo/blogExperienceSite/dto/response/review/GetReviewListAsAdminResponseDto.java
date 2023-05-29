package com.ssoyoo.blogExperienceSite.dto.response.review;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.view.ReviewListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GetReviewListAsAdminResponseDto extends ResponseDto {

    private List<ReviewAsAdminSummary> responseReviewList;

    public GetReviewListAsAdminResponseDto(List<ReviewListViewEntity> reviewEntityList) {
        super("SU", "Success");
        List<ReviewAsAdminSummary> responseReviewList = new ArrayList<>();
        for(ReviewListViewEntity reviewEntity : reviewEntityList){
            ReviewAsAdminSummary reviewSummary = new ReviewAsAdminSummary(reviewEntity);
            responseReviewList.add(reviewSummary);
        }
        this.responseReviewList = responseReviewList;
    }
}

@Getter
@Setter
@NoArgsConstructor
class ReviewAsAdminSummary{

    private String title;
    private String reviewTitle;
    private String reviewAddress;
    private int campaignEvaluation;
    private String opinion;



    public ReviewAsAdminSummary(ReviewListViewEntity reviewEntity){

        this.title = reviewEntity.getTitle();
        this.reviewTitle = reviewEntity.getReviewTitle();
        this.reviewAddress = reviewEntity.getReviewAddress();
        this.campaignEvaluation = reviewEntity.getCampaignEvaluation();
        this.opinion = reviewEntity.getOpinion();

    }


}
