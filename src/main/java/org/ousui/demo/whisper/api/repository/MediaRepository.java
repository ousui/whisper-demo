package org.ousui.demo.whisper.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ousui.demo.whisper.api.model.Media;

/**
 * @since 2025/06/03
 **/
public interface MediaRepository extends JpaRepository<Media, Long> {

    // Custom save method is not required as JpaRepository already provides save().

}
