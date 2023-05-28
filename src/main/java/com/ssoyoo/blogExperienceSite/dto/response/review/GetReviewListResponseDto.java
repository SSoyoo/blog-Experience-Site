package com.ssoyoo.blogExperienceSite.dto.response.review;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.ReviewEntity;
import com.ssoyoo.blogExperienceSite.entity.view.ReviewListViewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetReviewListResponseDto extends ResponseDto {

    private List<ReviewSummary> responseReviewList;

    public GetReviewListResponseDto(List<ReviewListViewEntity> reviewEntityList) {
        super("SU", "Success");
        List<ReviewSummary> responseReviewList = new ArrayList<>();
        for(ReviewListViewEntity reviewEntity : reviewEntityList){
            ReviewSummary reviewSummary = new ReviewSummary(reviewEntity);
            responseReviewList.add(reviewSummary);
        }
        this.responseReviewList = responseReviewList;
    }
}

@Getter
@Setter
@NoArgsConstructor
class ReviewSummary{

    private String title;
    private String reviewTitle;
    private String reviewAddress;



    public ReviewSummary(ReviewListViewEntity reviewEntity){

        this.title = reviewEntity.getTitle();
        this.reviewTitle = reviewEntity.getReviewTitle();
        this.reviewAddress = reviewEntity.getReviewAddress();

    }

}
