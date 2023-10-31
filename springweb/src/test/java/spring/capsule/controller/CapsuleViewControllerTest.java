package spring.capsule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import spring.capsule.domain.Capsule;
import spring.capsule.domain.User;
import spring.capsule.domain.UserRole;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.AddUserRequest;
import spring.capsule.repository.CapsuleRepository;
import spring.capsule.repository.UserRepository;
import spring.capsule.service.CapsuleService;
import spring.capsule.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 테스트 후 데이터 롤백
public class CapsuleViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CapsuleService capsuleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CapsuleRepository capsuleRepository;

    @Test
    public void whenSaveCapsule_thenReturnRedirect() throws Exception {
        // Given
        Capsule capsule = new Capsule(  "What is Java?", "Java is a programming language.", LocalDate.now());


        // When & Then
        mockMvc.perform(post("/capsule/chat")
                        .param("question", "What is Java?")
                        .param("answer", "Java is a programming language.")
                        .param("qnadate", "2023-10-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/capsules/1"));
    }

    @Test
    public void whenNewCapsuleWithId_thenReturnCapsuleView() throws Exception {
        // Given
        Capsule capsule = new Capsule( "What is Java?", "Java is a programming language.", LocalDate.now());


        // When & Then
        mockMvc.perform(get("/capsule/chat").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("capsule"))
                .andExpect(view().name("chat"));
    }
//        // 1. 사용자 추가
//        User user = User.builder()
//                .email("test@example.com")
//                .nickname("tester")
//                .password("test1234")
//                .role(UserRole.USER)
//                .build();
//        userRepository.save(user);
//
//        // 2. 캡슐 추가 요청 데이터 생성
//        AddCapsuleRequest addCapsuleRequest = new AddCapsuleRequest("최근에 자주 불면을 겪었나요?" , "네, 매일 밤 잠들기가 어렵습니다.", LocalDate.parse("2023-10-14"));
//
//
//        // 3. 캡슐 추가
//        Capsule capsule = addCapsuleRequest.toEntity();
//        capsule.setUser(user); // 캡슐과 사용자 연결
//        capsuleRepository.save(capsule);
//
//        // 4. 저장된 캡슐 검증
//        Capsule foundCapsule = capsuleRepository.findById(capsule.getQnaid()).orElseThrow();
//
//        assertThat(foundCapsule.getQuestion()).isEqualTo("최근에 자주 불면을 겪었나요?");
//        assertThat(foundCapsule.getAnswer()).isEqualTo("네, 매일 밤 잠들기가 어렵습니다.");
//        assertThat(foundCapsule.getQnadate()).isEqualTo(LocalDate.parse("2023-10-14"));
//        assertThat(foundCapsule.getUser()).isEqualTo(user);


}
