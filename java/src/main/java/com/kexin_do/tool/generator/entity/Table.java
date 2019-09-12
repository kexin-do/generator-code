package com.kexin_do.tool.generator.entity;

import java.util.List;

public class Table {
    private String tableName;

    private List<Column> Column;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<com.kexin_do.tool.generator.entity.Column> getColumn() {
        return Column;
    }

    public void setColumn(List<com.kexin_do.tool.generator.entity.Column> column) {
        Column = column;
    }
}
