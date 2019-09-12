package com.kexin_do.tool.generator.tools.plugins.dao.mybatis;

import com.kexin_do.tool.generator.tools.plugins.JavaAndJdbcType;
import com.kexin_do.tool.generator.tools.plugins.TableAndEntityDetails;

import java.util.Map;
import java.util.Set;

public class MybatisTemplateConstant {

    public static final String MAPPER_PATH = "MAPPER_PATH";

    public static final String ENTITY_NAME = "ENTITY_NAME";

    public static final String ENTITY_PATH = "ENTITY_PATH";

    public static final String RESULT_INFOS = "RESULT_INFOS";

    public static final String COLUMN_NAME = "COLUMN_NAME";

    public static final String COLUMN_JDBC_TYPE = "COLUMN_JDBC_TYPE";

    public static final String PROPERTY_NAME = "PROPERTY_NAME";

    private static String makeResult(Map.Entry<String, JavaAndJdbcType> cap) {
        return new String(RESULT_TEMPLATE).
                replaceAll(COLUMN_NAME, cap.getKey()).
                replaceAll(PROPERTY_NAME, cap.getValue().getProperty()).
                replaceAll(COLUMN_JDBC_TYPE, cap.getValue().getJdbcType());
    }
    public static final String RESULT_TEMPLATE = "\t\t<result column=\""+COLUMN_NAME+"\" jdbcType=\""+COLUMN_JDBC_TYPE+"\" property=\""+PROPERTY_NAME+"\"/>\n";

    public static final String KEY_TYPE = "KEY_TYPE";

    public static final String TABLE_NAME = "TABLE_NAME";

    public static final String TABLE_COLUMNS = "TABLE_COLUMNS";

    public static final String INSERT_INFOS = "INSERT_INFOS";

    private static String makeProperty(Map.Entry<String, JavaAndJdbcType> cap) {
        return new String(INSERT_PROPERTIES_TEMPLATE).
                replaceAll(PROPERTY_NAME, cap.getValue().getProperty()).
                replaceAll(COLUMN_JDBC_TYPE, cap.getValue().getJdbcType());
    }
    public static final String INSERT_PROPERTIES_TEMPLATE = "#{"+PROPERTY_NAME+",jdbcType="+COLUMN_JDBC_TYPE+"},";

    public static final String PRIMARY_KEY_COLUMN = "PRIMARY_KEY_COLUMN";

    public static final String PRIMARY_KEY_PROPERTY = "PRIMARY_KEY_PROPERTY";

    public static final String PRIMARY_KEY_JAVA_TYPE = "PRIMARY_KEY_JAVA_TYPE";

    public static final String PRIMARY_KEY_PROPERTY_JDBC_TYPE = "PRIMARY_KEY_PROPERTY_JDBC_TYPE";

    public static final String SELECT_IFS_INFOS = "SELECT_IFS_INFOS";

    private static String makeSelectIfs(Map.Entry<String, JavaAndJdbcType> cap) {
        return new String(SELECT_IFS_TEMPLATE).
                replaceAll(COLUMN_NAME, cap.getKey()).
                replaceAll(PROPERTY_NAME, cap.getValue().getProperty()).
                replaceAll(COLUMN_JDBC_TYPE, cap.getValue().getJdbcType());
    }
    public static final String SELECT_IFS_TEMPLATE = "\t\t\t<if test=\""+PROPERTY_NAME+" != null\">\n" +
            "\t\t\t\tAND "+COLUMN_NAME+" = #{"+PROPERTY_NAME+",jdbcType="+COLUMN_JDBC_TYPE+"}\n" +
            "\t\t\t</if>\n";

    public static final String UPDATE_IFS_INFOS = "UPDATE_IFS_INFOS";

    private static String makeUpdateIfs(Map.Entry<String, JavaAndJdbcType> cap) {
        return new String(UPDATE_IFS_TEMPLATE).
                replaceAll(COLUMN_NAME, cap.getKey()).
                replaceAll(PROPERTY_NAME, cap.getValue().getProperty()).
                replaceAll(COLUMN_JDBC_TYPE, cap.getValue().getJdbcType());
    }

    public static final String UPDATE_IFS_TEMPLATE = "\t\t\t<if test=\""+PROPERTY_NAME+" != null\">\n" +
            "\t\t\t\t"+COLUMN_NAME+" = #{"+PROPERTY_NAME+",jdbcType="+COLUMN_JDBC_TYPE+"},\n" +
            "\t\t\t</if>\n";

    public static String makeXmlFileString(TableAndEntityDetails taed) {

        Set<Map.Entry<String, JavaAndJdbcType>> capEntries = taed.getColumnsAndPropertiesMap().entrySet();

        StringBuilder resultInfos = new StringBuilder();
        StringBuilder selectIfsInfos = new StringBuilder();
        StringBuilder updateIfsInfos = new StringBuilder();
        StringBuilder insertInfos = new StringBuilder();
        for (Map.Entry<String, JavaAndJdbcType> cap: capEntries) {
            resultInfos.append(makeResult(cap));
            selectIfsInfos.append(makeSelectIfs(cap));
            updateIfsInfos.append(makeUpdateIfs(cap));
            insertInfos.append(makeProperty(cap));
        }

        return new String(XML_TEMPLATE).
                replaceAll(MAPPER_PATH, taed.getMapperPath()+"."+taed.getEntityName()+"Mapper").
                replaceAll(ENTITY_NAME, taed.getEntityName()).
                replaceAll(ENTITY_PATH, taed.getEntityPath()).
                replaceAll(TABLE_NAME, taed.getTableName()).
                replaceAll(TABLE_COLUMNS, taed.getTableColumns()).
                //replaceAll(ENTITY_PROPERTIES, taed.getEntityProperties()).
                replaceAll(PRIMARY_KEY_COLUMN, taed.getKeyableInfo().getColumn()).
                replaceAll(PRIMARY_KEY_PROPERTY_JDBC_TYPE, taed.getKeyableInfo().getJdbcType()).
                replaceAll(PRIMARY_KEY_PROPERTY, taed.getKeyableInfo().getProperty()).
                replaceAll(INSERT_INFOS, insertInfos.toString().substring(0, insertInfos.toString().length()-1)).
                replaceAll(RESULT_INFOS, resultInfos.toString()).
                replaceAll(SELECT_IFS_INFOS, selectIfsInfos.toString()).
                replaceAll(UPDATE_IFS_INFOS, updateIfsInfos.toString());
    }

    public static final String XML_TEMPLATE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                    "<mapper namespace=\""+MAPPER_PATH+"\">\n" +
                    "\t<resultMap id=\""+ENTITY_NAME+"ResultMap\"  type=\""+ENTITY_PATH+"\" >\n" +
                    RESULT_INFOS +
                    "\t</resultMap>\n\n" +

                    "\t<insert id=\"insert\" parameterType=\""+ENTITY_PATH+"\">\n" +
                    "\t\tINSERT INTO "+TABLE_NAME+"("+TABLE_COLUMNS+") \n" +
                    "\t\t\tVALUES("+INSERT_INFOS+")\n" +
                    "\t</insert>\n\n" +

                    "\t<select id=\"selectByPrimaryKey\" resultMap=\""+ENTITY_NAME+"ResultMap\" parameterType=\""+KEY_TYPE+"\">\n" +
                    "\t\tSELECT "+TABLE_COLUMNS+" FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_COLUMN+" = #{"+PRIMARY_KEY_PROPERTY+", jdbcType="+PRIMARY_KEY_PROPERTY_JDBC_TYPE+"}\n" +
                    "\t</select>\n\n" +

                    "\t<select id=\"selectByCondition\" resultMap=\""+ENTITY_NAME+"ResultMap\" parameterType=\"java.util.Map\">\n" +
                    "\t\tSELECT\n" +
                    "\t\t\t"+TABLE_COLUMNS+"\n " +
                    "\t\tFROM\n" +
                    "\t\t\t"+TABLE_NAME+"\n" +
                    "\t\tWHERE 1=1\n" +
                    SELECT_IFS_INFOS +
                    "\t</select>\n\n" +

                    "\t<update id=\"updateByPrimaryKey\" parameterType=\""+ENTITY_PATH+"\">\n" +
                    "\t\tUPDATE "+TABLE_NAME+"\n " +
                    "\t\t<set>\n" +
                    UPDATE_IFS_INFOS +
                    "\t\t</set>\n" +
                    "\t\tWHERE "+PRIMARY_KEY_COLUMN+" = #{"+PRIMARY_KEY_PROPERTY+", jdbcType="+PRIMARY_KEY_PROPERTY_JDBC_TYPE+"}\n" +
                    "\t</update>\n\n" +

                    "\t<delete id=\"deleteByPrimaryKey\" parameterType=\""+ENTITY_PATH+"\">\n" +
                    "\t\tDELETE FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_COLUMN+" = #{"+PRIMARY_KEY_PROPERTY+", jdbcType="+PRIMARY_KEY_PROPERTY_JDBC_TYPE+"}\n" +
                    "\t</delete>\n" +
                    "</mapper>";


    public static String makeMapperString(TableAndEntityDetails taed) {
        return new String(MAPPER_TEMPLATE).
                replaceAll(MAPPER_PATH, taed.getMapperPath()).
                replaceAll(ENTITY_PATH, taed.getEntityPath()).
                replaceAll(ENTITY_NAME, taed.getEntityName()).
                replaceAll(PRIMARY_KEY_JAVA_TYPE, taed.getKeyableInfo().getJavaType());
    }
    public static final String MAPPER_TEMPLATE =
            "package "+MAPPER_PATH+";\n" +
                    "\n" +
                    "import java.util.Map;\n" +
                    "import java.util.List;\n" +
                    "import "+ENTITY_PATH+";\n" +
                    "\n" +
                    "public class "+ENTITY_NAME+"Mapper {\n" +
                    "\n" +
                    "    int insert("+ENTITY_NAME+" o);\n" +
                    "\n" +
                    "    int deleteByPrimaryKey("+PRIMARY_KEY_JAVA_TYPE+" id);\n" +
                    "\n" +
                    "    int updateByPrimaryKey("+ENTITY_NAME+" o);\n" +
                    "\n" +
                    "    "+ENTITY_NAME+" selectByPrimaryKey("+PRIMARY_KEY_JAVA_TYPE+" id);\n" +
                    "\n" +
                    "    List<"+ENTITY_NAME+"> selectByCondition(Map<String, Object> param);\n" +
                    "}\n";
}
