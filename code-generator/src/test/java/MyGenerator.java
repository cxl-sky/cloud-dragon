import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.List;

public class MyGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/user-center/user-server/src/main/java");  //生成路径(一般都是生成在此项目的src/main/java下面)
        globalConfig.setAuthor("chenxiaolong"); //设置作者
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true); //第二次生成会把第一次生成的覆盖掉
        globalConfig.setServiceName("%sService"); //生成的service接口名字首字母是否为I，这样设置就没有
        globalConfig.setBaseResultMap(true); //生成resultMap
        globalConfig.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(globalConfig);

        //2、数据源配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/user_center?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dataSource.setDriverName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        mpg.setDataSource(dataSource);

        // 3、包配置
        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setModuleName("server");
        packageInfo.setParent("com.dragon.user");
        mpg.setPackageInfo(packageInfo);

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperControllerClass("com.dragon.base.BaseController");
        strategy.setSuperEntityClass("com.dragon.base.BaseEntity");
        strategy.setTablePrefix("sys_"); // 表名前缀
        strategy.setEntityLombokModel(true); //使用lombok
        strategy.setRestControllerStyle(true);
        strategy.setInclude("sys_role_menu");  // 逆向工程使用的表   如果要生成多个,这里可以传入String[]
        mpg.setStrategy(strategy);

//        ConfigBuilder config = new ConfigBuilder(packageInfo, dataSource, strategy, mpg.getTemplate(), globalConfig);
//        ConfigBuilder config = new ConfigBuilder(null, dataSource, null, null, null);
//
//        List<TableInfo> tableInfoList = config.getTableInfoList();
//        System.out.println(tableInfoList);
        //5、执行
        mpg.execute();
    }

}