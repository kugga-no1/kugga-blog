package com.li.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Scanner;

/**
 * 改进了mpgenerator 在多模块下可以自动打入不同包
 */
public class MpGeneratorUtilsAutoParentUtil {

    public static void main(String[] args) {
        String[] models = {"blog-web", "blog-service", "blog-api"};
        String include[]=null;
        include=scanner("表名，多个英文逗号分割").split(",");
        for (String model : models) {
            shell(model,include);
        }
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNoneBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }




        private static void shell(String model,String ...include){
        File file = new File(model);
        String path = file.getAbsolutePath();
        //path = path.substring(0, path.lastIndexOf(File.separator));
            AutoGenerator mpg = new AutoGenerator();
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir(path+"/src/main/java");
//        gc.setOutputDir("D:\\workspace-sts\\0520adv\\02_mp_springboot/src/main/java");
        gc.setAuthor("kugga");
        gc.setOpen(false);//当代码生成完成之后是否打开代码所在的文件夹
        gc.setFileOverride(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
//        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/kugga-blog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("fhnbyyb123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.li");//controller entity  service  service.impl
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("api");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper.xml");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置字段和表名的是否把下划线完成驼峰命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置生成的实体类继承的父类
//        strategy.setSuperEntityClass("com.sxt.BaseEntity");
        strategy.setEntityLombokModel(true);//是否启动lombok
        strategy.setRestControllerStyle(true);//是否生成resetController
        // 公共父类
//        strategy.setSuperControllerClass("com.sxt.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("person_id","person_name");
        //要设置生成哪些表 如果不设置就是生成所有的表
        strategy.setInclude(include);
        //strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName()+"tb_");
        mpg.setStrategy(strategy);

            // 关闭默认 xml 生成，调整生成 至 根目录
            TemplateConfig tc = new TemplateConfig();
            if ("blog-web".equals(model)) {
                tc.setEntity(null);
                tc.setService(null);
                tc.setServiceImpl(null);
                tc.setMapper(null);
                tc.setXml(null);
            } else if ("blog-service".equals(model)) {
                tc.setController(null);
                tc.setEntity(null);
                tc.setService(null);
            }  else if ("blog-api".equals(model)) {
                tc.setController(null);
                tc.setServiceImpl(null);
                tc.setMapper(null);
                tc.setXml(null);
            }
            mpg.setTemplate(tc);


        mpg.execute();
    }
}
