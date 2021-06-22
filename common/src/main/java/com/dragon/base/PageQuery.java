package com.dragon.base;

import lombok.Data;
import lombok.Setter;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月22日 下午4:14
 */
@Setter
public class PageQuery {

    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageNum() {
        if (this.pageNum==null) {
            return 1;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize==null) {
            return 10;
        }
        return pageSize;
    }
}
