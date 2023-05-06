package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignInRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.SignUpRequestDto;
import com.ssoyoo.blogExperienceSite.dto.request.user.UpdateUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.dto.response.User.SignInResponseDto;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImplement(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

        String email = dto.getEmail();
        String nickname = dto.getNickname();
        String blogAddress = dto.getBlogAddress();
        String phoneNumber = dto.getPhoneNumber();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        try {
            // 이메일중복
            boolean isExistEmail = userRepository.existsByEmail(email);
            if (isExistEmail) return CustomResponse.existentEmail();
            // 닉네임중복
            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if (isExistNickname) return CustomResponse.existentNickname();
            // 블로그주소중복
            boolean isExistBlogAddress = userRepository.existsByBlogAddress(blogAddress);
            if (isExistBlogAddress) return CustomResponse.existentBlog();
            // 휴대폰번호 중복
            boolean isExistPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (isExistPhoneNumber) return CustomResponse.existentPhoneNumber();
            // 새 유저 정보 save
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            // 데이터베이스 오류반환
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }

        // 성공결과 반환
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
            if (userEntity == null) return CustomResponse.SignInFail();

            String encodedPassword = userEntity.getPassword();
            boolean isEqualPassword = passwordEncoder.matches(password, encodedPassword);
            if (!isEqualPassword) return CustomResponse.SignInFail();

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



        try {
            boolean isExistEmail = userRepository.existsByEmail(email);
            if (!isExistEmail) return CustomResponse.authenticationFail();
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return CustomResponse.success();
    }

}
