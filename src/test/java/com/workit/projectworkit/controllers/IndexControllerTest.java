package com.workit.projectworkit.controllers;

import com.workit.projectworkit.services.IndexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {


    @Mock
    IndexService indexService;

    @InjectMocks
    IndexController indexController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    void postFile() throws Exception {
        String s = "Bonjour\n" +
                "Simon\n" +
                "C'est\n" +
                "Moi";
        MockMultipartFile file
                = new MockMultipartFile(
                        "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                s.getBytes()
        );

        mockMvc.perform(multipart("/upload").file(file)
                    .param("word-out", "Simon"))
                .andExpect(status().isOk());

        verify(indexService).filterFile(any(), anyString());
    }
}