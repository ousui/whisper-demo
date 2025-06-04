// TranslationTask.java
package org.ousui.demo.whisper.api.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
public class TranslationTask {

    @Id
    private String id;

    private Long mediaId;

    private String content;
    /**
     * 状态理应有抽取中，翻译中，完成等
     */
    private int status;
    private String result;
}