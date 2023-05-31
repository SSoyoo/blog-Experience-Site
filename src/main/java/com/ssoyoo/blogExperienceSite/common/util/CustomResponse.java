package com.ssoyoo.blogExperienceSite.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;

public class CustomResponse {

    public static ResponseEntity<ResponseDto> success(){
        ResponseDto body = new ResponseDto("SU","SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static ResponseEntity<ResponseDto> databaseError() {

        ResponseDto errorBody = 
            new ResponseDto("DE", "Database Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> validationFail() {

        ResponseDto errorBody = 
            new ResponseDto("VF", "validation Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existentEmail() {

        ResponseDto errorBody = 
            new ResponseDto("EM", "existent Email");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existentNickname() {

        ResponseDto errorBody = 
            new ResponseDto("EN", "Existent User Nickname");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existentBlog() {

        ResponseDto errorBody = 
            new ResponseDto("EB", "Existent BlogAddress");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existentPhoneNumber() {

        ResponseDto errorBody = 
            new ResponseDto("EP", "Existent PhoneNumber");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> signInFail() {

        ResponseDto errorBody = 
            new ResponseDto("SF", "Sign In failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> authenticationFail() {

        ResponseDto errorBody = 
            new ResponseDto("AF", "Authentication failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> passwordMisMatch() {

        ResponseDto errorBody = 
            new ResponseDto("PM", "Password mismatch");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> noExistUser() {

        ResponseDto errorBody = new ResponseDto("NU", "Non-Existent User");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> noExistCampaign() {

        ResponseDto errorBody = new ResponseDto("NC", "Non-Existent Campaign");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existApplication() {

        ResponseDto errorBody = new ResponseDto("EA", "Existent Application");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> applicationPeriodPassed() {

        ResponseDto errorBody = new ResponseDto("PP", "application period has passed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existFavorite() {

        ResponseDto errorBody = new ResponseDto("EF", "Existent Favorite");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> noExistFavorite() {

        ResponseDto errorBody = new ResponseDto("NF", "Non-Existent Favorite");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> noExistApplication() {

        ResponseDto errorBody = new ResponseDto("NA", "Non-Existent Application");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);

    }


    public static ResponseEntity<ResponseDto> noPermission() {

        ResponseDto errorBody = new ResponseDto("NP", "No permission");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> existReview() {
        ResponseDto errorBody = new ResponseDto("ER", "Existent Review");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> doNotSelectUser() {
        ResponseDto errorBody = new ResponseDto("NS", "Do not selected User");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> excessOfRecruitment() {
        ResponseDto errorBody = new ResponseDto("ER", "Excess Of Recruitment");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> selectionIsDone() {
        ResponseDto errorBody = new ResponseDto("SD", "Reviewer selection Is Done ");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }
}
