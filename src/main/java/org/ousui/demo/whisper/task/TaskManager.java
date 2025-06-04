package org.ousui.demo.whisper.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.ousui.demo.whisper.api.model.Media;
import org.ousui.demo.whisper.api.model.TranslationTask;
import org.ousui.demo.whisper.api.repository.MediaRepository;
import org.ousui.demo.whisper.api.repository.TranslationTaskRepository;
import org.ousui.demo.whisper.support.llm.LlmApi;
import org.ousui.demo.whisper.support.whisper.WhisperApi;
import org.ousui.demo.whisper.support.whisper.WhisperConsumer;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 2025/06/03
 **/
@Component
@EnableScheduling
public class TaskManager {

    @Resource
    private MediaRepository mediaRepository;

    @Resource
    private TranslationTaskRepository translationTaskRepository;

    @Resource
    private WhisperApi whisperApi;

    @Resource
    private WhisperConsumer whisperConsumer;

    @Resource
    private LlmApi llmApi;

    private List<String> targetLanguages = List.of("Simplified_Chinese", "Traditional_Chinese", "Japanese");

    @Scheduled(cron = "0 * * * * ?")

    public void cron() {
        // 理应按照状态查询翻译任务
        translationTaskRepository.findAll().forEach(task -> {

            Media media = mediaRepository.findById(task.getMediaId()).orElse(null);

            String uuid = whisperApi.extract(media.getPath(), null);

            String content = whisperConsumer.getContent(uuid);
            if (content == null) {
                return;
            }
            task.setContent(content);
            Map<String, String> reuslt = new HashMap<>();
            targetLanguages.forEach(language -> {
                String r = llmApi.translate(content, "English", language);
                reuslt.put(language, r);
            });
            try {
                task.setResult(new ObjectMapper().writeValueAsString(reuslt));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            //
            task.setStatus(1);
            translationTaskRepository.save(task);
        });
    }

}
