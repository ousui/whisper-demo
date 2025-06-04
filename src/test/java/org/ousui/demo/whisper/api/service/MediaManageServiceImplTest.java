package org.ousui.demo.whisper.api.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author Blade.T
 * @since 2025/06/03
 **/
@SpringBootTest
class MediaManageServiceImplTest {

    @Resource
    private MediaManageService mediaManageService;

    @Test
    void upload() {
        mediaManageService.upload(new File(""));
    }
}