package hexlet.code.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }

}