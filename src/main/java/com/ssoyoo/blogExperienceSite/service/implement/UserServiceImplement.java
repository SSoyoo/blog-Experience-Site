package com.ssoyoo.blogExperienceSite.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.request.user.PostUserRequestDto;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import com.ssoyoo.blogExperienceSite.entity.UserEntity;
import com.ssoyoo.blogExperienceSite.repository.UserRepository;
import com.ssoyoo.blogExperienceSite.service.UserService;

import net.bytebuddy.asm.Advice.Return;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplement(
        UserRepository userRepository,
        BCryptPasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto) {

    
        String email = dto.getEmail();
        String nickname = dto.getNickname();
        String blogAddress = dto.getBlogAddress();
        String phoneNumber = dto.getPhoneNumber();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        try {
            //이메일중복
            boolean isExistEmail = userRepository.existsByEmail(email);
            if(isExistEmail) return CustomResponse.existentEmail();
            //닉네임중복
            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if(isExistNickname) return CustomResponse.existentNickname();
            //블로그주소중복
            boolean isExistBlogAddress = userRepository.existsByBlogAddress(blogAddress);
            if(isExistBlogAddress)  return CustomResponse.existentBlog();
            //휴대폰번호 중복
            boolean isExistPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if(isExistPhoneNumber) return CustomResponse.existentPhoneNumber();
            //새 유저 정보 save
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            //데이터베이스 오류반환
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }

        //성공결과 반환
        return CustomResponse.success();
        
        
    }
    
}
