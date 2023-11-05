package spring.capsule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.capsule.domain.Message;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse {

    private List<Choice> choices;//선택지 리스트 저장 필드

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice { // 선택지 표현 클래스로 인덱스와 메시지 저장

        private int index;
        private Message message;

    }

}
