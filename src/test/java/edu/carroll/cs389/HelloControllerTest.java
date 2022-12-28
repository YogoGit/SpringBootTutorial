package edu.carroll.cs389;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexTest() throws Exception {
        mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from Spring Boot!")));
    }

    @Test
    public void userTest() throws Exception {
        final String name = "Student";
        mockMvc.perform(get("/user").param("name", name)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("Hello")))
                .andExpect(content().string(endsWith(name + "!")));
    }
}
