package com.quan.datn.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class FileController {

    @GetMapping("/uploads/{year}/{month}/{day}/{fileName}")
    @CrossOrigin
    public ResponseEntity<ByteArrayResource> getFile(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            @PathVariable("day") String day,
            @PathVariable("fileName") String fileName) {
        if (Strings.isNotEmpty(fileName)) {
            try {
                Path filePath = Paths.get("uploads/" + year + "/" + month  + "/" + day, fileName);
                byte[] buffer = Files.readAllBytes(filePath);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.valueOf(Files.probeContentType(filePath)))
                        .body(byteArrayResource);
            } catch (Exception ignored) {
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
