package com.dragon.generator.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.dragon.generator.query.GeneratorParams;
import com.dragon.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月21日 上午9:50
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @GetMapping("/tables")
    public Result<List<TableInfo>> getTables(DataSourceConfig dataSource) {
        ConfigBuilder config = new ConfigBuilder(null, dataSource, null, null, null);
        List<TableInfo> tableInfoList = config.getTableInfoList();
        return Result.success(tableInfoList);
    }

    @PostMapping("/tables")
    public Result<Object> generatorCode(GeneratorParams generatorParams) {
        DataSourceConfig dataSource = generatorParams.getDataSource();
        GlobalConfig globalConfig = generatorParams.getGlobalConfig();
        PackageConfig packageInfo = generatorParams.getPackageInfo();
        StrategyConfig strategy = generatorParams.getStrategy();

        AutoGenerator generator = new AutoGenerator();
        generator.setDataSource(dataSource);
        generator.setGlobalConfig(globalConfig);
        generator.setPackageInfo(packageInfo);
        generator.setStrategy(strategy);

        generator.execute();

        return Result.success(null);
    }
}
