package ru.interviewservice.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.interviewservice.repository.InterviewRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Unit-level testing for InterviewControllerTest")
public class InterviewControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InterviewRepository repository;

    @BeforeEach
    public void setup() {
        openMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(new InterviewController(repository)).build();
    }

    @Test
    public void shouldReturnView() throws Exception {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("list_interview"))
                .andExpect(model().attributeExists("sorter", "filter", "items", "item"));
    }
}