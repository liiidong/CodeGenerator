package generator.config.toolkit;

import generator.config.po.TableField;
import generator.config.rules.DbType;

import java.util.List;

public class IntfSqlMapUtil {

	public static String genBaseResultMapSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName,DbType dbType) {
		StringBuilder sql = new StringBuilder();
		StringBuilder keySql = new StringBuilder();
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String filed = tableFields.get(i).getPropertyName();
			String type = tableFields.get(i).getType();
			/*if(type.equalsIgnoreCase("VARCHAR2")||type.contains("VARCHAR")){
				type="VARCHAR";
			}
			if(type.contains("int")){
				type="INTEGER";
			}*/
			
			if (tableFields.get(i).isKeyFlag()) {
				keySql.append("<id column=\"").append(clumn + "\" ").append(" property=\"").append(filed + "\" ")
						//.append("jdbcType=\"").append(type).append("\"").append("/>").append("\n\t");
				        .append("/>").append("\n\t");
			} else {

				sql.append("<result column=\"").append(clumn + "\" ").append(" property=\"").append(filed + "\" ")
						//.append("jdbcType=\"").append(type).append("\"").append("/>").append("\n\t");
				        .append("/>").append("\n\t");
			}
		}
		keySql.append(sql.toString());
		return keySql.toString();
	}

	public static String genBatchDeleteSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM  ").append(entityTableName).append(" WHERE ").append(pkClumn).append(" IN")
				.append("\n\t");
		sql.append("  <foreach item=\"")
				.append(pkName + "\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">").append("\n\t");
		sql.append("  #{").append(pkName).append("}").append("\n\t");
		sql.append("  </foreach>");
		return sql.toString();
	}

	public static String genDeleteSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(entityTableName);
		sql.append(" WHERE ").append(pkClumn).append("=#{").append(pkName).append("}");
		return sql.toString();
	}

	public static String genBaseColumn(List<TableField> tableFields) {
		StringBuilder sql = new StringBuilder();
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			if (i < size - 1) {
				sql.append("tm.").append(clumn).append(",");
			} else {
				sql.append("tm.").append(clumn);
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		return sql.toString();
	}

	public static String genEntityWhereClause(List<TableField> tableFields) {
		StringBuilder sql = new StringBuilder();
		sql.append("<where>\n\t");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String field = tableFields.get(i).getPropertyName();
			StringBuilder ifSql = new StringBuilder();
			String type = tableFields.get(i).getType();
			boolean isDateType=false;
			if (type.equalsIgnoreCase("TIMESTAMP") || type.equalsIgnoreCase("DATE")|| type.equalsIgnoreCase("DATETIME")) {
				ifSql.append("      <if test=\"%s!=null \" >\n\t");
				isDateType=true;
			} else {
				ifSql.append("      <if test=\"%s!=null and %s!=\'\'\" >\n\t");
			}
			ifSql.append("\t AND %s=#{%s} \n\t");
			ifSql.append("      </if>\t");
			if(isDateType){
				sql.append(String.format(ifSql.toString(), field, "tm.".concat(clumn), field));
			}else{
				sql.append(String.format(ifSql.toString(), field, field, "tm.".concat(clumn), field));
			}
			sql.append("\n\t");
		}
		
        //sql.append("<include refid=\"Authority_WHERE_Clause\" />\n\t");
		sql.append("  </where>\n\t");
		return sql.toString();
	}

	public static String genInsertSql(List<TableField> tableFields, String entityTableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(entityTableName).append("(").append("\n\t");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			if (i < size - 1) {
				sql.append(clumn).append(",");
			} else {
				sql.append(clumn);
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\t");
		sql.append(") VALUES (");
		sql.append("\n\t");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String filed = tableFields.get(i).getPropertyName();
			if (i < size - 1) {
				sql.append("#{").append(filed).append("},");
			} else {
				sql.append("#{").append(filed).append("}");
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\t");
		sql.append(")");
		return sql.toString();
	}

	public static String genUpdateSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(entityTableName).append("\n\t").append(" <set> ").append("\n\t");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String field = tableFields.get(i).getPropertyName();
			StringBuilder ifSql = new StringBuilder();
			if (!pkClumn.equals(clumn)) {
				ifSql.append("  <if test=\"%s!=null\" >\n\t");
				ifSql.append("\t  %s=#{%s},\n\t");
				ifSql.append("  </if>\t");
				sql.append(String.format(ifSql.toString(), field, clumn, field, field));
				sql.append("\n\t");
			}
		}
		sql.append("</set>").append("\n\t");
		// sql.append("\tupdateTime = SYSDATE\n\t");
		sql.append("WHERE ").append(pkClumn).append("=#{").append(pkName).append("}");
		return sql.toString();
	}

	public static String genGetByIdSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT \n\t");
		sql.append("  <include refid=\"Base_Column_List\" />");
		sql.append("\n\t  FROM ").append(entityTableName.concat(" tm")).append(" WHERE ").append("tm.".concat(pkClumn)).append("=#{").append(pkName)
				.append("}");
		return sql.toString();
	}

	public static String genGetListSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		// select
		// <include refid="Base_Column_List" />
		// from MOB_APP
		// where APP_ID = #{appId,jdbcType=VARCHAR}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT \n\t");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String field = tableFields.get(i).getPropertyName();
			if (clumn.equalsIgnoreCase(field)) {
				sql.append(clumn);
			} else {
				sql.append(clumn).append(" AS ").append(field);
			}
			if (i < size - 1) {
				sql.append(",");
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\tFROM ").append(entityTableName).append(" WHERE 1=1");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String field = tableFields.get(i).getPropertyName();
			StringBuilder ifSql = new StringBuilder();
			ifSql.append("\n\t<#if %s?exists && %s!=\"\" >\n");
			if (i < size - 1) {
				ifSql.append("\t\tAND %s=:%s\n");
			} else {
				ifSql.append("\t\tAND %s=:%s\n");
			}
			ifSql.append("\t</#if>");
			sql.append(String.format(ifSql.toString(), field, field, clumn, field));
		}
		return sql.toString();
	}

	public static String genGetTotalSql(List<TableField> tableFields, String entityTableName, String pkClumn,
			String pkName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ").append(entityTableName).append(" WHERE 1=1");
		for (int i = 0, size = tableFields.size(); i < size; i++) {
			String clumn = tableFields.get(i).getName();
			String field = tableFields.get(i).getPropertyName();
			StringBuilder ifSql = new StringBuilder();
			ifSql.append("\n\t<#if %s?exists && %s!=\"\" >\n");
			if (i < size - 1) {
				ifSql.append("\t\tAND %s=:%s\n");
			} else {
				ifSql.append("\t\tAND %s=:%s\n");
			}
			ifSql.append("\t</#if>");
			sql.append(String.format(ifSql.toString(), field, field, clumn, field));
		}
		return sql.toString();
	}

}