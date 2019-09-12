package com.kexin_do.tool.generator.tools.plugins;

import com.kexin_do.tool.generator.entity.Column;
import com.kexin_do.tool.generator.entity.Table;
import com.kexin_do.tool.generator.tools.other.StringUtils;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableAndEntityDetailsFactory {

    private static final String defaultPath = "com.kexin_do.";

    public static TableAndEntityDetails getInstance(
            String mapperPath, String servicePath, String controllerPath,
            String xmlPath, String entityPath, @NonNull Table table) throws Exception {

        TableAndEntityDetails current = new TableAndEntityDetails();
        current.setEntityPath(!StringUtils.isEmpty(entityPath) ? entityPath : defaultPath + "entity");
        current.setServicePath(!StringUtils.isEmpty(servicePath) ? servicePath : defaultPath + "service");
        current.setControllerPath(!StringUtils.isEmpty(controllerPath) ? controllerPath : defaultPath + "controller");
        current.setXmlPath(!StringUtils.isEmpty(xmlPath) ? xmlPath : defaultPath + "mapping");
        current.setMapperPath(!StringUtils.isEmpty(mapperPath) ? mapperPath : defaultPath + "mapper");

        current.setEntityName(StringUtils.arrayToCamelString(table.getTableName()));
        current.setTableName(table.getTableName());

        List<Column> columns = table.getColumn();
        Map<String, JavaAndJdbcType> columnsAndPropertiesMap = new HashMap<>(((columns.size() * 4 / 3) + 1));
        boolean haveKey = false;
        StringBuilder tableColumns = new StringBuilder();
        StringBuilder entityProperties = new StringBuilder();
        for (Column column : columns) {
            JavaAndJdbcType jajt = new JavaAndJdbcType();
            jajt.setColumn(column.getField());
            jajt.setProperty(StringUtils.arrayToCamelString(column.getField()));
            JavaAndJdbcType.JdbcTypeAndJavaType javaTypeAndJdbcType = JavaAndJdbcType.getJavaTypeAndJdbcType(column.getType());
            if (javaTypeAndJdbcType == null) {
                throw new Exception("未找到类型映射：" + column.toString());
            }
            jajt.setJavaType(javaTypeAndJdbcType.getJava());
            jajt.setJdbcType(javaTypeAndJdbcType.getJdbc());
            if (column.isKeyable()) {
                if(!haveKey){
                    haveKey = true;
                    current.setKeyableInfo(jajt);
                }else {
                    current.getFinalMsg().put("warning key","所要生成的表不止一个主键");
                }
            }
            tableColumns.append(jajt.getColumn()).append(",");
            entityProperties.append(jajt.getProperty()).append(",");
            columnsAndPropertiesMap.put(column.getField(), jajt);
        }

        if(!haveKey){
            throw new Exception("未找到表的主键：" + table);
        }
        current.setTableColumns(tableColumns.toString().substring(0, tableColumns.toString().length()-1));
        current.setEntityProperties(entityProperties.toString().substring(0, entityProperties.toString().length()-1));
        current.setColumnsAndPropertiesMap(columnsAndPropertiesMap);
        return current;
    }
}
