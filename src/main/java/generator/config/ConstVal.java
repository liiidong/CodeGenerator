package generator.config;

import java.io.File;
import java.nio.charset.Charset;

/**
 * 定义常量
 */
public class ConstVal {

    public static final String MODULENAME = "ModuleName";

    public static final String DTO = "DTO";
    public static final String ENTITY = "Entity";
    public static final String SERIVCE = "Service";
    public static final String SERVICEIMPL = "ServiceImpl";
    public static final String MAPPER = "Mapper";
    public static final String XML = "Xml";
    public static final String CONTROLLER = "Controller";

    public static final String ENTITY_PATH = "entity_path";
    public static final String BASE_PATH = "base_path";
    public static final String SERIVCE_PATH = "serivce_path";
    public static final String SERVICEIMPL_PATH = "serviceimpl_path";
    public static final String MAPPER_PATH = "mapper_path";
    public static final String XML_PATH = "xml_path";
    public static final String CONTROLLER_PATH = "controller_path";
    public static final String DTO_PATH = "dto_path";

    public static final String JAVA_TMPDIR = "java.io.tmpdir";
    public static final String UTF8 = Charset.forName("UTF-8").name();
    public static final String UNDERLINE = "_";

    public static final String JAVA_SUFFIX = ".java";
    public static final String XML_SUFFIX = ".xml";

    public static final String BASE_ENTITY = "BaseEntity";
    public static final String BASE_SERVICE = "BaseService";
    public static final String BASE_CONTROLLER = "BaseController";
    public static final String COMMON_PAGE = "CommonPage";
    public static final String RETURNRESULT = "ReturnResult";
    public static final String BASE_MAPPER = "BaseMapper";

    public static final String TEMPLATE_ENTITY = "/templates/entity.java.vm";
    public static final String TEMPLATE_BASE_ENTITY = "/templates/baseEntity.java.vm";
    public static final String TEMPLATE_DTO = "/templates/dto.java.vm";
    public static final String TEMPLATE_MAPPER = "/templates/mapper.java.vm";
    public static final String TEMPLATE_BASE_MAPPER = "/templates/baseMapper.java.vm";
    public static final String TEMPLATE_XML = "/templates/sqlMap.xml.vm";
    public static final String TEMPLATE_BASE_SERVICE = "/templates/baseService.java.vm";
    public static final String TEMPLATE_SERVICE = "/templates/service.java.vm";
    public static final String TEMPLATE_SERVICEIMPL = "/templates/serviceImpl.java.vm";
    public static final String TEMPLATE_CONTROLLER = "/templates/controller.java.vm";
    public static final String TEMPLATE_BASE_CONTROLLER = "/templates/baseController.java.vm";
    public static final String TEMPLATE_COMMON_PAGE = "/templates/commonPage.java.vm";
    public static final String TEMPLATE_RETURNRESULT = "/templates/returnResult.java.vm";

    public static final String ENTITY_NAME = File.separator + "%s" + JAVA_SUFFIX;

    // 配置使用classloader加载资源
    public static final String VM_LOADPATH_KEY = "file.resource.loader.class";
    public static final String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    public static final String SUPERD_MAPPER_CLASS = "com.enough.mapper.BaseMapper";
    public static final String SUPERD_SERVICE_CLASS = "com.enough.service.BaseService";
    public static final String SUPERD_SERVICEIMPL_CLASS = "com.enough.service.impl.ServiceImpl";

}
