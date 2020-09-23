package com.example.image.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片实体类
 *
 * @author lsy
 * @version 1.0
 * @date 2020-09-23 9:13
 **/
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ImageCode {

    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}