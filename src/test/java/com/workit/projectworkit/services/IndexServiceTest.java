package com.workit.projectworkit.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IndexServiceTest {

    MultipartFile file;
    String wordOut;

    IndexService indexService;

    @BeforeEach
    void setUp() throws IOException{

        indexService = new IndexService();

        Path path = Paths.get("./src/test/resources/testFile.txt");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = Files.readAllBytes(path);

        file = new MockMultipartFile(name,
                originalFileName, contentType, content);

        wordOut = "Simon";
    }

    @Test
    void filterFile() throws IOException {
        InputStream inputStream = indexService.filterFile(file, wordOut);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        assertEquals("Bonjour", reader.readLine());
        assertEquals("C'est", reader.readLine());
        assertEquals("Moi", reader.readLine());
    }
}