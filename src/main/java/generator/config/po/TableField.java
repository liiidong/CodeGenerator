package generator.config.po;

import generator.config.StrategyConfig;
import generator.config.rules.DbColumnType;
import generator.config.toolkit.StringUtils;

/**
 * 表字段信息
 */
public class TableField {

    private boolean convert;
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    private String name;
    private String type;
    private String propertyName;
    private DbColumnType columnType;
    private String comment;

    public boolean isConvert() {
        return convert;
    }

    protected void setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.isCapitalModeNaming(name)) {
            this.convert = false;
        } else {
            // 转换字段
            if (StrategyConfig.DB_COLUMN_UNDERLINE) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!name.equals(propertyName)) {
                this.convert = true;
            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isKeyIdentityFlag() {
        return keyIdentityFlag;
    }

    public void setKeyIdentityFlag(boolean keyIdentityFlag) {
        this.keyIdentityFlag = keyIdentityFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
        this.setConvert(strategyConfig);
    }

    public DbColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(DbColumnType columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 按JavaBean规则来生成get和set方法
     */
    public String getCapitalName() {
        if (propertyName.length() <= 1) {
            return propertyName.toUpperCase();
        }
        // 第一个字母 小写、 第二个字母 大写 ，特殊处理
        String firstChar = propertyName.substring(0, 1);
        if (Character.isLowerCase(firstChar.toCharArray()[0])
                && Character.isUpperCase(propertyName.substring(1, 2).toCharArray()[0])) {
            return firstChar.toLowerCase() + propertyName.substring(1);
        }
        return firstChar.toUpperCase() + propertyName.substring(1);
    }

}
