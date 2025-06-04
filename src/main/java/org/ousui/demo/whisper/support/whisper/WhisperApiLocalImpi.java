package org.ousui.demo.whisper.support.whisper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @since 2025/06/03
 **/
@Service
@Slf4j
public class WhisperApiLocalImpi implements WhisperApi {

    private final Map<String, Process> cache = new HashMap<>();

    // 使用线程池调用
    private Executor executor = Executors.newSingleThreadExecutor();

    @Resource
    private WhisperConsumer consumer;

    @Override
    public String extract(String path, WhisperArgs args) {
        String uuid = UUID.randomUUID().toString();

        log.info("开始抽取数据：{}", path);
        // 控制队列大小
        // 异步执行
        executor.execute(() -> {
            Process process = null;
            BufferedReader reader = null;
            try {
                // 动态获取外部程序路径
                String whisperPath = System.getenv("WHISPER_PATH");
                if (whisperPath == null || whisperPath.isEmpty()) {
//                    throw new RuntimeException("未设置 WHISPER_PATH 环境变量");
                    whisperPath = "whisper";
                }

                // whisper 有两种获取数据的方式，会将数据生成为文件
                // 参数调整
                String[] command = {
                    whisperPath,
                    "--model", "large-v3",
                    "--output_format", "txt",
                    path
                };
                process = Runtime.getRuntime().exec(command);
                cache.put(uuid, process);

                // 读取输出
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info("\t 转换结果：{}", line);
                    content.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                consumer.consumer(uuid, content.toString());
                log.info("\t 进程退出码：{}", exitCode);
            } catch (Exception e) {
                log.error("执行外部程序时发生错误：", e);
            } finally {
                // 清理资源
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        log.warn("关闭输入流时发生错误：", e);
                    }
                }
                if (process != null) {
                    process.destroy();
                }
            }
        });

        return uuid;
    }

    @Override
    public boolean abort(String uuid) {
        try {
            Process process = cache.get(uuid);
            if (process != null) {
                process.destroyForcibly();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
