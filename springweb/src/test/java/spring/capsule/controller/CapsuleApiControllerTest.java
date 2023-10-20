package spring.capsule.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring.capsule.domain.Capsule;
import spring.capsule.dto.AddCapsuleRequest;
import spring.capsule.dto.UpdateCapsuleRequest;
import spring.capsule.repository.CapsuleRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CapsuleApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    CapsuleRepository capsuleRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        capsuleRepository.deleteAll();
    }

    @DisplayName("addCapsule: 블로그 글 추가에 성공한다.")
    @Test
    public void addCapsule() throws Exception {
        // given 블로그 글 추가에 필요한 요청 객체 만듦
        final String url = "/api/capsules";
        final String question = "question";
        final String answer = "answer";
        final AddCapsuleRequest userRequest = new AddCapsuleRequest(question, answer);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when 글 추가 API 에  JSON타입 요청 보냄 -given의 요청객체 url을 써서 같이 보냄
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then 응답코드가 ㅏ201Created인지 확인
        result.andExpect(status().isCreated());

        List<Capsule> capsules = capsuleRepository.findAll();

        assertThat(capsules.size()).isEqualTo(1);//크기가 1인지
        assertThat(capsules.get(0).getQuestion()).isEqualTo(question);
        assertThat(capsules.get(0).getAnswer()).isEqualTo(answer);
    }

    @DisplayName("findAllCapsules: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllCapsules() throws Exception {
        // given
        final String url = "/api/capsules";
        final String question = "question";
        final String answer = "answer";

        capsuleRepository.save(Capsule.builder()
                .question(question)
                .answer(answer)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].answer").value(answer))
                .andExpect(jsonPath("$[0].question").value(question));
    }

    @DisplayName("findCapsule: 블로그 글 조회에 성공한다.")
    @Test
    public void findCapsule() throws Exception {
        // given
        final String url = "/api/capsules/{id}";
        final String question = "question";
        final String answer = "answer";

        Capsule savedCapsule = capsuleRepository.save(Capsule.builder()
                .question(question)
                .answer(answer)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedCapsule.getQnaid()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answer").value(answer))
                .andExpect(jsonPath("$.question").value(question));
    }
/*
    @DisplayName("deleteCapsule: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteCapsule() throws Exception {
        // given
        final String url = "/api/capsules/{id}";
        final String question = "question";
        final String answer = "answer";

        Capsule savedCapsule = capsuleRepository.save(Capsule.builder()
                .question(question)
                .answer(answer)
                .build());

        // when
        mockMvc.perform(delete(url, savedCapsule.getQnaid()))
                .andExpect(status().isOk());

        // then
        List<Capsule> capsules = capsuleRepository.findAll();

        assertThat(capsules).isEmpty();
    }

    @DisplayName("updateCapsule: 블로그 글 수정에 성공한다.")
    @Test
    public void updateCapsule() throws Exception {
        // given
        final String url = "/api/capsules/{id}";
        final String question = "question";
        final String answer = "answer";

        Capsule savedCapsule = capsuleRepository.save(Capsule.builder()
                .question(question)
                .answer(answer)
                .build());

        final String newQuestion = "new question";
        final String newAnswer = "new answer";

        UpdateCapsuleRequest request = new UpdateCapsuleRequest(newQuestion, newAnswer);

        // when
        ResultActions result = mockMvc.perform(put(url, savedCapsule.getQnaid())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isOk());

        Capsule capsule = capsuleRepository.findById(savedCapsule.getQnaid()).get();

        assertThat(capsule.getQuestion()).isEqualTo(newQuestion);
        assertThat(capsule.getAnswer()).isEqualTo(newAnswer);
    }
*/
}


