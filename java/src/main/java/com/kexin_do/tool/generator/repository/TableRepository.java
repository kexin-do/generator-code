package com.kexin_do.tool.generator.repository;

import com.kexin_do.tool.generator.entity.Column;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends Repository<Column, String> {

    @Query("select COLUMN_NAME field," +
            " CASE IS_NULLABLE WHEN 'YES' THEN 'true' ELSE 'false' END AS nullable," +
            " DATA_TYPE type," +
            " CASE COLUMN_KEY WHEN 'PRI' THEN 'true' ELSE 'false' END AS keyable," +
            " COLUMN_COMMENT description" +
            " from information_schema.columns " +
            " where table_name = :table_name")
    List<Column> getTableDetail(@Param("table_name") String table_name);

    @Query("select TABLE_NAME tableName " +
            " from information_schema.TABLES" +
            " where TABLE_SCHEMA=(select database()) limit :page, :size")
    List<String> getTableNames(@Param("page") Integer page, @Param("size") Integer size);
}
