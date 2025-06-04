package org.ousui.demo.whisper.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 **/
@RequestMapping("/manager/media")
public class MediaManageController {

    /**
     * 媒体文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedia(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        // Logic to save the file and create a Media entity can be added here
        return ResponseEntity.ok("File uploaded successfully");
    }
}
