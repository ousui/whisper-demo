package org.ousui.demo.whisper.api.repository;

import org.ousui.demo.whisper.api.model.TranslationTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @since 2025/06/03
 **/
public interface TranslationTaskRepository extends JpaRepository<TranslationTask, String> {
}
