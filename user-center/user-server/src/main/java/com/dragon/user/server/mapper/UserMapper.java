package com.dragon.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.user.client.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
