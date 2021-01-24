package com.ndky.infooms.common;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * @author chenqingsheng
 * @date 2021/1/20 14:25
 */
public class CodeGenerator {
    public static void main(String[] args) {

        /**全局配置
         *
         */
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("C:/Users/Administrator/Desktop/companyproject/info-oms/src/main/java/");
        gc.setAuthor("chenqingsheng");
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);


        /** 数据源配置
         *
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/ndky?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");


        /** 包名配置
         *
         */
        PackageConfig pc = new PackageConfig();
        // 设置放在哪个父包下面
        // pc.setModuleName("模块")
        pc.setParent("com.ndky.infooms");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");

        /**策略配置
         *
         */
        StrategyConfig strategy = new StrategyConfig();
        // 设置要要映射的表名
        strategy.setInclude("sys_menu_role");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("is_deleted");
        strategy.setTableFillList(
                Arrays.asList(
                        new TableFill("utime", FieldFill.INSERT_UPDATE),
                        new TableFill("ctime", FieldFill.INSERT))
        );

        /**乐观锁配置
         *
         */
        strategy.setVersionFieldName("version");
        // 驼峰命名
        strategy.setRestControllerStyle(true);
        // 使用localhost:8080/hello_id_2这种风格
        strategy.setRestControllerStyle(true);



        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setPackageInfo(pc);
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}