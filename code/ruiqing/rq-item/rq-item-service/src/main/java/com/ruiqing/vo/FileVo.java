package com.ruiqing.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/18 14:08
 */

public class FileVo {
    private String name;
    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
