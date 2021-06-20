package com.dragon.user.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月20日 下午12:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private Integer menuId;
    private Integer parentId;
    private String url;
    private String code;
    private String component;
    private Integer status;
    private String redirect;
    private String title;
    private String icon;
    private Integer orderNum;

}
