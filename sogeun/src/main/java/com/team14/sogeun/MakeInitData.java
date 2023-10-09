package com.team14.sogeun;

import com.team14.sogeun.domain.entity.UserRole;
import com.team14.sogeun.domain.entity.User;
import com.team14.sogeun.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MakeInitData { 
	
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    


    @PostConstruct
    public void makeAdminAndUser() {
        if(!userRepository.existsByLoginId("admin1")) {
            User admin1 = User.builder()
                    .loginId("admin1")
                    .password(encoder.encode("1234"))
                    .nickname("관리자1")
                    .role(UserRole.ADMIN)
                    .build();
            userRepository.save(admin1);
        }


    }
    
}
