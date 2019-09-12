package com.kexin_do.tool.generator.entity;

public class Column {

    private String field;

    private String type;

    private boolean nullable;

    private boolean keyable;

    private String description;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isKeyable() {
        return keyable;
    }

    public void setKeyable(boolean keyable) {
        this.keyable = keyable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Column{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", nullable=" + nullable +
                ", keyable=" + keyable +
                ", description='" + description + '\'' +
                '}';
    }
}
