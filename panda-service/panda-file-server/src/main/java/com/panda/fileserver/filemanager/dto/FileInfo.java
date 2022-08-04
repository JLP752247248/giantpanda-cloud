package com.panda.fileserver.filemanager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-04  10:08
 * @Description: 文件, 文件存储目录directory/uuid
 * @Version: 1.0
 */
@Data
@Builder
public class FileInfo {

    private String url;
    private String uuid;
    private String name;
    private Integer type = 0;
    private String directory = "";
    private String md5;

}
