package com.workit.projectworkit.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexService {

    public InputStream filterFile(MultipartFile multipartFile, String wordOut) throws IOException {

        InputStream inputStreamInitial = multipartFile.getInputStream();

        List<String> listLinesFiltered = new BufferedReader(new InputStreamReader(inputStreamInitial, StandardCharsets.UTF_8))
                .lines()
                .filter(s -> !s.equals(wordOut))
                .collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for(String line : listLinesFiltered) {
            String lineOutput = line + '\n';
            byteArrayOutputStream.write(lineOutput.getBytes());
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();
        return new ByteArrayInputStream(bytes);
    }
}
