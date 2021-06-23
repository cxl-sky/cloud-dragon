package com.dragon.generator.query;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月21日 上午10:20
 */
@Data
public class GeneratorParams {

    /**
     * 数据库配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;
    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

}
