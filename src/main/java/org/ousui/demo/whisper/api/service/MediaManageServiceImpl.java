package org.ousui.demo.whisper.api.service;

import jakarta.annotation.Resource;
import org.ousui.demo.whisper.api.model.Media;
import org.ousui.demo.whisper.api.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

/**
 * @author Blade.T
 * @since 2025/06/03
 **/
@Service
public class MediaManageServiceImpl implements MediaManageService {

    @Resource
    private MediaRepository mediaRepository;

    @Override
    public boolean upload(File mediaFile) {

        // 保存媒体文件到 oss
//        mediaFile

        Media entity = new Media();
        String ossPath = "";
        entity.setId(new Random().nextLong());
        entity.setPath(ossPath);
        entity.setStatus(1);
        mediaRepository.save(entity);
        return true;
    }
}
