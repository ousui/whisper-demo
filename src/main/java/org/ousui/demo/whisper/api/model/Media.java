package org.ousui.demo.whisper.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @since 2025/06/03
 **/
@Data
@Entity
public class Media {

    @Id
    private Long id;

    private String path;

    private int status;
}
