package org.ousui.demo.whisper.support.whisper;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @since 2025/06/03
 **/
@Service
public class WhisperApiRemoteImpi implements WhisperApi {

    private final Map<String, Process> cache = new HashMap<>();

    @Override
    public String extract(String path, WhisperArgs args) {
        String uuid = UUID.randomUUID().toString();

        // 使用 rmi 的方式进行调用远程节点的 whisper
        // 使用 mq 传输数据

        return uuid;
    }

    @Override
    public boolean abort(String uuid) {
        try {
            cache.get(uuid).destroyForcibly();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
