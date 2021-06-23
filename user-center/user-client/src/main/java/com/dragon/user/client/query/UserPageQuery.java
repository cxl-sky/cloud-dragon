package com.dragon.user.client.query;

import com.dragon.base.PageQuery;
import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月22日 下午4:16
 */
@Data
public class UserPageQuery extends PageQuery {

    private String usernameLike;

    private Integer status;

    private Long roleId;

}
