package com.ssoyoo.blogExperienceSite.dto.request.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SelectReviewerRequestDto {

    @NotNull
    private Integer campaignId;
    @NotNull @Size(min = 0)
    private List<Integer> userList;

}
