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

    public static ResponseEntity<ResponseDto> SignInFail() {

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
            new ResponseDto("AF", "Password mismatch");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

    }


    


    
}
