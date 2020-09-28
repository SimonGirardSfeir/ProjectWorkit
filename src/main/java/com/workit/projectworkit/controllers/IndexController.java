package com.workit.projectworkit.controllers;

import com.workit.projectworkit.services.IndexService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping("/upload")
    public @ResponseBody byte[] postFile(@RequestParam("file") MultipartFile file, @RequestParam("word-out") String wordOut) throws IOException {
        InputStream in = indexService.filterFile(file, wordOut);
        return IOUtils.toByteArray(in);
    }
}
