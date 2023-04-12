package hexlet.code.app.Controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DBRider
@DataSet("my_users.yml")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void positiveTestCreateUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"petr@yahoo.com\",\n" +
                                "    \"firstName\": \"Petr\",\n" +
                                "    \"lastName\": \"Sidorov\",\n" +
                                "    \"password\": \"password123\"\n" +
                                "}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Petr", "petr@yahoo.com");
    }

    @Test
    public void negativeTestCreateUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"petr@yahoo.com\",\n" +
                                "    \"firstName\": \"Petr\",\n" +
                                "    \"lastName\": \"Sidorov\",\n" +
                                "    \"password\": \"ff\"\n" +
                                "}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void positiveTestGetUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/users/1"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("John", "Smith");
    }

    @Test
    public void negativeTestGetUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/users/11"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void testGetAllUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/users"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("John", "Smith");
        assertThat(response.getContentAsString()).contains("Ivan", "Ivanov");
        assertThat(response.getContentAsString()).contains("Vlad", "Smith");
        assertThat(response.getContentAsString()).contains("Dmitri", "Antonov");
    }

    @Test
    public void positiveTestUpdateUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"petr@yahoo.com\",\n" +
                                "    \"firstName\": \"Petr\",\n" +
                                "    \"lastName\": \"Sidorov\",\n" +
                                "    \"password\": \"password123\"\n" +
                                "}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Petr", "petr@yahoo.com");
    }

    @Test
    public void negativeTestUpdateNotExistingUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(put("/users/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"petr@yahoo.com\",\n" +
                                "    \"firstName\": \"Petr\",\n" +
                                "    \"lastName\": \"Sidorov\",\n" +
                                "    \"password\": \"password123\"\n" +
                                "}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void negativeTestUpdateInvalidUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"petr@yahoo.com\",\n" +
                                "    \"firstName\": \"Petr\",\n" +
                                "    \"lastName\": \"Sidorov\",\n" +
                                "    \"password\": \"f3\"\n" +
                                "}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void positiveTestDeleteUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(delete("/users/4"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);

        MockHttpServletResponse response1 = mockMvc
                .perform(get("/users"))
                .andReturn().getResponse();
        assertThat(response1.getStatus()).isEqualTo(200);
        assertThat(response1.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response1.getContentAsString()).doesNotContain("Dmitri", "Antonov");
    }

    @Test
    public void negativeTestDeleteUser() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(delete("/users/11"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
    }
}
