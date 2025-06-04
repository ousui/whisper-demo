package org.ousui.demo.whisper.support.whisper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Blade.T
 * @since 2025/06/03
 **/
@SpringBootTest
class WhisperApiLocalImpiTest {

    @Resource
    private WhisperApi whisperApiLocalImpi;

    @Value("classpath:mp3/1.mp3")
    private File testFile;

    @Test
    void extract() throws InterruptedException {
        assertNotNull(testFile, "Test file should not be null");
        String uuid = whisperApiLocalImpi.extract(testFile.getAbsolutePath(), null);
        System.out.println(uuid);
        TimeUnit.MINUTES.sleep(1);
    }
}