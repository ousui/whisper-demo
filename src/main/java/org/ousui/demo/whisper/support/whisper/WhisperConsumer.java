package org.ousui.demo.whisper.support.whisper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用该 consumer 进行数据接收
 *
 * @since 2025/06/03
 **/
@Service
public class WhisperConsumer {

    private Map<String, String> cache = new HashMap<>();

    public String getContent(String uuid) {

        return cache.get(uuid);
    }

    public void consumer(String uuid, String content) {
        cache.putIfAbsent(uuid, content);
    }

}
