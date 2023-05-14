package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdatePasswordRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdateUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.GetMyInfoResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;



    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

        String email = dto.getEmail();
        String nickname = dto.getNickname();
        String blogAddress = dto.getBlogAddress();
        String phoneNumber = dto.getPhoneNumber();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        try {

            boolean isExistEmail = userRepository.existsByEmail(email);
            if (isExistEmail) return CustomResponse.existentEmail();

            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if (isExistNickname) return CustomResponse.existentNickname();

            boolean isExistBlogAddress = userRepository.existsByBlogAddress(blogAddress);
            if (isExistBlogAddress) return CustomResponse.existentBlog();

            boolean isExistPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (isExistPhoneNumber) return CustomResponse.existentPhoneNumber();

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {

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
        String ROLE = "USER";

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return CustomResponse.signInFail();

            String encodedPassword = userEntity.getPassword();
            boolean isEqualPassword = passwordEncoder.matches(password, encodedPassword);
            if (!isEqualPassword) return CustomResponse.signInFail();

            String jwt = jwtTokenProvider.createJwt(email, ROLE);
            body = new SignInResponseDto(jwt);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @Override
    public ResponseEntity<ResponseDto> updateUser(String email, UpdateUserRequestDto dto) {

        String password = dto.getPassword();
        String nickname = dto.getNickname();
        String blogAddress = dto.getBlogAddress();
        String phoneNumber = dto.getPhoneNumber();
        String profileImageUrl = dto.getProfileImageUrl();
        String homeAddress = dto.getHomeAddress();


        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return CustomResponse.authenticationFail();

            String encodedPassword = userEntity.getPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);
            if(!isEqualPassword) return CustomResponse.passwordMisMatch();

            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if(isExistNickname) return CustomResponse.existentNickname();

            boolean isExistBlogAddress = userRepository.existsByBlogAddress(blogAddress);
            if(isExistBlogAddress) return CustomResponse.existentBlog();

            boolean isExistPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if(isExistPhoneNumber) return CustomResponse.existentPhoneNumber();


            userEntity.setNickname(nickname);
            userEntity.setBlogAddress(blogAddress);
            userEntity.setPhoneNumber(phoneNumber);
            userEntity.setProfileImageUrl(profileImageUrl);
            userEntity.setHomeAddress(homeAddress);

            userRepository.save(userEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> updatePassword(String email, UpdatePasswordRequestDto dto) {

        String currentPassword = dto.getCurrentPassword();
        String encodedPasswordPasswordToChange =
                passwordEncoder.encode(dto.getPasswordToChange());

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return  CustomResponse.authenticationFail();

            String encodedPassword = userEntity.getPassword();
            boolean isEqualPassword = passwordEncoder.matches(currentPassword,encodedPassword);
            if(!isEqualPassword) return CustomResponse.passwordMisMatch();

            userRepository.save(userEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userEmail) {

        GetMyInfoResponseDto body = null;


        try {

            UserEntity userEntity = userRepository.findByEmail(userEmail);
            if(userEntity == null) return CustomResponse.authenticationFail();

            body = new GetMyInfoResponseDto(userEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
