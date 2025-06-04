package org.ousui.demo.whisper.api.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.ousui.demo.whisper.api.service.TranslationTaskService;
import org.ousui.demo.whisper.api.model.TranslationTask;

@RestController
@RequestMapping("/api/translation")
public class TranslationTaskController {

    @Resource
    private TranslationTaskService translationTaskService;

    @PostMapping("/create")
    public ResponseEntity<TranslationTask> createTranslationTask(@RequestBody TranslationTask task) {
        TranslationTask createdTask = translationTaskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/query/{taskId}")
    public ResponseEntity<TranslationTask> queryTranslationTask(@PathVariable String taskId) {
        TranslationTask task = translationTaskService.queryTask(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/cancel/{taskId}")
    public ResponseEntity<Void> cancelTranslationTask(@PathVariable String taskId) {
        translationTaskService.cancelTask(taskId);
        return ResponseEntity.noContent().build();
    }

}