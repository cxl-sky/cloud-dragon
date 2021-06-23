package com.dragon.user.client.query;

import com.dragon.base.PageQuery;
import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月22日 下午4:16
 */
@Data
public class RolePageQuery extends PageQuery {

    private String roleNameLike;


}
