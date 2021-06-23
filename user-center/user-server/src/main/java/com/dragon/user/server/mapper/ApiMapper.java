package com.dragon.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.user.client.entity.Api;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 接口管理 Mapper 接口
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@Mapper
public interface ApiMapper extends BaseMapper<Api> {

}
