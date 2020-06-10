package main;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import generator.AutoGenerator;
import generator.config.DataSourceConfig;
import generator.config.GlobalConfig;
import generator.config.PackageConfig;
import generator.config.StrategyConfig;
import generator.config.converts.MySqlTypeConvert;
import generator.config.rules.DbType;
import generator.config.rules.NamingStrategy;

/**
 * 代码生成器演示
 */
public class ImsGenerator {

    /**
     * 生成演示
     */
    public static void main(String[] args) {
        /* 获取 JDBC 配置文件 */
        Properties props = getProperties();
        AutoGenerator mpg = new AutoGenerator();

        String outputDir = "d:/code";//此文件夹会生成到D:目录下
        //String viewOutputDir = outputDir + "/view/";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 开启 超类泛型 模式
        gc.setAuthor("enough");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName(props.getProperty("db.jdbc.driver"));
        dsc.setUsername(props.getProperty("db.user"));
        dsc.setPassword(props.getProperty("db.password"));
        dsc.setUrl(props.getProperty("db.url"));
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        // strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        //strategy.setInclude(new String[] { "oa_attachment_info","oa_att_updateinfo","oa_business_dept_mapping","oa_business_person_mapping","oa_buttoninfo","oa_common_tenant_config","oa_data_update_info","oa_dept_tenant_relationship","oa_flownode_btn_config","oa_flownode_ext_attribute","oa_flownode_ext_field","oa_fw_cc_info","oa_gw_config","oa_gw_tenant_source","oa_gw_user_config","oa_manual_no_other","oa_manualnumbering","oa_manual_num_use","oa_old_dept_info","oa_old_tenant_info","oa_hq_fw_basicinfo","oa_huiqianinfo","oa_qianbao_form","oa_relation_file_record","oa_report_info","oa_shouwen_form","oa_shouweninfo","oa_tenant_btn_config","oa_tenant_target_info","oa_tenant_trace_config","oa_workflow_btn_config","oa_workflow_extension"}); // 需要生成的表
        //strategy.setInclude(new String[] { "oa_role_mapped_ab","oa_role_user_b","oa_leader_secretary_mapping"}); // 需要生成的表
        strategy.setInclude(new String[] {"config_properties"});
        // strategy.setExclude(new String[]{"test"}); // 排除需要生成的表
        // 自定义实体父类
        strategy.setSuperEntityClass("com.enough.test.entity.BaseEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.enough.test.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.enough.test.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.enough.test.TestServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("com.enough.test.controller.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        strategy.setSuperMapperClass("com.enough.test.dao.BaseMapper");
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);  //所属模块
        pc.setParent("com.enough.test"); // 自定义包路径
        pc.setController("controller"); // 控制器包名，默认 web
        pc.setServiceImpl("service.impl"); // service包名，默认service.impl
        pc.setService("service");//service interface包名，默认service
        pc.setEntity("entity");
        pc.setDto("dto");
        pc.setXml("mapper");
        pc.setMapper("dao");
        pc.setBase("base");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/generator-config/application.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}