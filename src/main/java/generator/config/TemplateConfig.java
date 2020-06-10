package generator.config;

/**
 * 模板路径配置项
 */
public class TemplateConfig {

    private String dto = ConstVal.TEMPLATE_DTO;

    private String entity = ConstVal.TEMPLATE_ENTITY;
    private String baseEntity = ConstVal.TEMPLATE_BASE_ENTITY;

    private String service = ConstVal.TEMPLATE_SERVICE;

    private String baseService = ConstVal.TEMPLATE_BASE_SERVICE;

    private String serviceImpl = ConstVal.TEMPLATE_SERVICEIMPL;

    private String mapper = ConstVal.TEMPLATE_MAPPER;

    private String baseMapper = ConstVal.TEMPLATE_BASE_MAPPER;

    private String xml = ConstVal.TEMPLATE_XML;

    private String controller = ConstVal.TEMPLATE_CONTROLLER;
    private String baseController = ConstVal.TEMPLATE_BASE_CONTROLLER;
    private String commonPage = ConstVal.TEMPLATE_COMMON_PAGE;
    private String returnResult = ConstVal.TEMPLATE_RETURNRESULT;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getBaseService() {
        return baseService;
    }

    public void setBaseService(String baseService) {
        this.baseService = baseService;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getBaseController() {
        return baseController;
    }

    public void setBaseController(String baseController) {
        this.baseController = baseController;
    }

    public String getCommonPage() {
        return commonPage;
    }

    public void setCommonPage(String commonPage) {
        this.commonPage = commonPage;
    }

    public String getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(String baseMapper) {
        this.baseMapper = baseMapper;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public String getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(String baseEntity) {
        this.baseEntity = baseEntity;
    }
}
