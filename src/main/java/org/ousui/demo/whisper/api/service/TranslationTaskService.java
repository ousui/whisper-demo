// TranslationTaskService.java
package org.ousui.demo.whisper.api.service;

import org.springframework.stereotype.Service;
import org.ousui.demo.whisper.api.model.TranslationTask;

@Service
public interface TranslationTaskService {

    /**
     * 创建翻译任务
     *
     * @param task
     * @return
     */
    TranslationTask createTask(TranslationTask task);

    /**
     * 查询翻译任务
     *
     * @param taskId
     * @return
     */
    TranslationTask queryTask(String taskId);

    /**
     * 取消翻译任务
     *
     * @param taskId
     */
    void cancelTask(String taskId);
}