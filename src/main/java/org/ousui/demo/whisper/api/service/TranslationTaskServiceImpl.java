// TranslationTaskService.java
package org.ousui.demo.whisper.api.service;

import jakarta.annotation.Resource;
import org.ousui.demo.whisper.api.model.Media;
import org.ousui.demo.whisper.api.model.TranslationTask;
import org.ousui.demo.whisper.api.repository.MediaRepository;
import org.ousui.demo.whisper.api.repository.TranslationTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class TranslationTaskServiceImpl implements TranslationTaskService {

    @Resource
    private TranslationTaskRepository translationTaskRepository;

    @Resource
    private MediaRepository mediaRepository;

    public TranslationTask createTask(TranslationTask task) {

        long mediaId = task.getMediaId();
        Media media = mediaRepository.findById(mediaId).orElse(null);
        Assert.notNull(media, "未找到媒体文件");
        // 保存任务到数据库



        // 这里可以使用触发器、扫表、异步事件等开启翻译任务
        return translationTaskRepository.save(task);
    }

    public TranslationTask queryTask(String taskId) {
        // 查询任务状态
        return translationTaskRepository.findById(taskId).orElse(null);
    }

    public void cancelTask(String taskId) {
        // 取消任务
        translationTaskRepository.deleteById(taskId);
    }
}