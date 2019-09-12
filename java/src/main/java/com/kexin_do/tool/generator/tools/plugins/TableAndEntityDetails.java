package com.kexin_do.tool.generator.tools.plugins;

import java.util.HashMap;
import java.util.Map;

public class TableAndEntityDetails {

    public static final String filePrefix = "D://tempFile//";

    private String tableName;

    private String tableColumns;

    private JavaAndJdbcType keyableInfo;

    private Map<String, JavaAndJdbcType> columnsAndPropertiesMap;

    private String entityName;

    private String entityProperties;

    private String entityPath;

    private String servicePath;

    private String mapperPath;

    private String xmlPath;

    private String controllerPath;

    private Map<String, String> finalMsg = new HashMap<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(String tableColumns) {
        this.tableColumns = tableColumns;
    }


    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityProperties() {
        return entityProperties;
    }

    public void setEntityProperties(String entityProperties) {
        this.entityProperties = entityProperties;
    }


    public Map<String, JavaAndJdbcType> getColumnsAndPropertiesMap() {
        return columnsAndPropertiesMap;
    }

    public void setColumnsAndPropertiesMap(Map<String, JavaAndJdbcType> columnsAndPropertiesMap) {
        this.columnsAndPropertiesMap = columnsAndPropertiesMap;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public JavaAndJdbcType getKeyableInfo() {
        return keyableInfo;
    }

    public void setKeyableInfo(JavaAndJdbcType keyableInfo) {
        this.keyableInfo = keyableInfo;
    }

    public Map<String, String> getFinalMsg() {
        return finalMsg;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    @Override
    public String toString() {
        return "TableAndEntityDetails{" +
                "tableName='" + tableName + '\'' +
                ", tableColumns='" + tableColumns + '\'' +
                ", keyableInfo=" + keyableInfo +
                ", columnsAndPropertiesMap=" + columnsAndPropertiesMap +
                ", entityName='" + entityName + '\'' +
                ", entityProperties='" + entityProperties + '\'' +
                ", entityPath='" + entityPath + '\'' +
                ", servicePath='" + servicePath + '\'' +
                ", mapperPath='" + mapperPath + '\'' +
                ", xmlPath='" + xmlPath + '\'' +
                ", controllerPath='" + controllerPath + '\'' +
                ", finalMsg=" + finalMsg +
                '}';
    }
}
