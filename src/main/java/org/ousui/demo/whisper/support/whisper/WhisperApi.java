package org.ousui.demo.whisper.support.whisper;

/**
 * WhisperApi，提供 whisper 的接口供其他模块调用
 *
 * @since 2025/06/03
 **/
public interface WhisperApi {

    /**
     * 从路径提取媒体文件的内容，并返回提取id
     *
     * @param path
     * @return
     */
    String extract(String path, WhisperArgs args);

    /**
     * 终止抽取任务
     */
    boolean abort(String id);



}
