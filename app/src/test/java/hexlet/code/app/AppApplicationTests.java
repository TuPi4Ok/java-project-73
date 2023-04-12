package hexlet.code.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AppApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testRootPage() throws Exception {
        // Выполняем запрос и получаем ответ
        MockHttpServletResponse response = mockMvc
                // Выполняем GET запрос по указанному адресу
                .perform(get("/welcome"))
                // Получаем результат MvcResult
                .andReturn()
                // Получаем ответ MockHttpServletResponse из класса MvcResult
                .getResponse();

        // Проверяем статус ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что ответ содержит определенный текст
        assertThat(response.getContentAsString()).contains("Welcome to Spring");
    }

}
