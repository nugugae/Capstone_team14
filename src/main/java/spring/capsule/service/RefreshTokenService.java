package spring.capsule.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.capsule.domain.RefreshToken;
import spring.capsule.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}

