package org.ousui.demo.whisper.support.llm;

/**
 * llm 翻译引擎 具体实现方式不赘述
 *
 * @since 2025/06/03
 **/
public interface LlmApi {

    /**
     * 翻译接口
     *
     * @param content
     * @param sourceLang
     * @param targetLang
     * @return
     */
    String translate(String content, String sourceLang, String targetLang);

}
