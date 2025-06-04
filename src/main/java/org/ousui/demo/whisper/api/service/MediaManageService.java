package org.ousui.demo.whisper.api.service;

import java.io.File;

/**
 * @since 2025/06/03
 **/
public interface MediaManageService {

    /**
     * 媒体文件上传接口
     *
     * @param mediaFile
     * @return
     */
    boolean upload(File mediaFile);

}
