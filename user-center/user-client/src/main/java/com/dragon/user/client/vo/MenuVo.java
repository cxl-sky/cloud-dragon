package com.dragon.user.client.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月20日 下午12:45
 */
@Data
public class MenuVo {

    private Long menuId;
    private Long parentId;
    private String url;
    private String code;
    private String component;
    private Integer status;
    private String redirect;
    private String title;
    private String icon;
    private Integer orderNum;
    private List<MenuVo> children;

}
