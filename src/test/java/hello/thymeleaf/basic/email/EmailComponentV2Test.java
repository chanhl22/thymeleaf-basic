package hello.thymeleaf.basic.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmailComponentV2Test {

    @Autowired
    private EmailComponentV2 emailComponent;

    @DisplayName("타임리프 템플릿을 사용해서 이메일 내용을 HTML로 생성한다.")
    @Test
    public void makeEmailBody() {
        //given
        String input = "테스트 코드에서 실행하기!";

        //when
        String result = emailComponent.makeEmailBody(input);

        //then
        assertThat(result).contains(input);
    }

}