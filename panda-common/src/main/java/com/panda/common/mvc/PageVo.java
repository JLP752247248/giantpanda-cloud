package com.panda.common.mvc;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-16  14:16
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PageVo implements Serializable {

    private int currPage = 0;

    private int pageSize = 10;

    private long totalSize;

}
