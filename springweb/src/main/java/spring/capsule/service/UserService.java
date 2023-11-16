package spring.capsule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.capsule.domain.User;
import spring.capsule.domain.UserRole;
import spring.capsule.dto.AddUserRequest;
import spring.capsule.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {//213

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .role(UserRole.USER)
                .build()).getUid();
    }

    //jwt 토큰서비스 추가 246
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No user found with email: " + email));
    }
    public List<User> findAll(){return  userRepository.findAll();}
    //기존 - >추가
    // 사용자의 로그인 ID로 사용자를 찾는 메서드


}