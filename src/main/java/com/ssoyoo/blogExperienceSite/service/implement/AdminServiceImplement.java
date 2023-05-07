package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.admin.AdminSignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.admin.UpdateAdminRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.entity.AdminEntity;
import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;
import com.ssoyoo.blogExperienceSite.repository.AdminRepository;
import com.ssoyoo.blogExperienceSite.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImplement implements AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public ResponseEntity<ResponseDto> signUp(AdminSignUpRequestDto dto) {

        String adminEmail = dto.getAdminEmail();
        String adminPhoneNumber = dto.getAdminPhoneNumber();
        String encodedPassword = passwordEncoder.encode(dto.getAdminPassword());
        dto.setAdminPassword(encodedPassword);


        try {
            boolean isExistEmail = adminRepository.existsByAdminEmail(adminEmail);
            if(isExistEmail) return CustomResponse.existentEmail();

            boolean isExistPhoneNumber = adminRepository.existsByAdminPhoneNumber(adminPhoneNumber);
            if(isExistPhoneNumber) return CustomResponse.existentPhoneNumber();

            AdminEntity adminEntity = new AdminEntity(dto);
            adminRepository.save(adminEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        SignInResponseDto body = null;
        String email = dto.getEmail();
        String password = dto.getPassword();
        String ROLE = "ADMIN";

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(email);
            if(adminEntity == null) return CustomResponse.signInFail();

            String encodedPassword = adminEntity.getAdminPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);
            if(!isEqualPassword) return CustomResponse.signInFail();

            String token = jwtTokenProvider.createJwt(email,ROLE);
            body = new SignInResponseDto(token);



        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> updateAdmin(String email, UpdateAdminRequestDto dto) {

        String password = dto.getPassword();
        String phoneNumber = dto.getPhoneNumber();


        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(email);
            if(adminEntity == null) return CustomResponse.authenticationFail();
            String encodedPassword = adminEntity.getAdminPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);
            if(!isEqualPassword) return CustomResponse.passwordMisMatch();

            boolean isExistPhoneNumber = adminRepository.existsByAdminPhoneNumber(phoneNumber);
            if(phoneNumber!= null && !isExistPhoneNumber) adminEntity.setAdminPhoneNumber(phoneNumber);

            adminRepository.save(adminEntity);



        }catch (Exception exception){
            exception.printStackTrace();
            CustomResponse.databaseError();
        }


        return CustomResponse.success();
    }
}
