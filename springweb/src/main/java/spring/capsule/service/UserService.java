package spring.capsule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.capsule.domain.User;
import spring.capsule.domain.UserRole;
import spring.capsule.dto.AddUserRequest;
import spring.capsule.repository.UserRepository;

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
                .build()).getId();
    }
    //jwt 토큰서비스 추가 246
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}