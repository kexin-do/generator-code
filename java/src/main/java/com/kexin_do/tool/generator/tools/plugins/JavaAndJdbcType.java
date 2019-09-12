package com.kexin_do.tool.generator.tools.plugins;

import com.kexin_do.tool.generator.tools.other.StringUtils;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaAndJdbcType {

    private String jdbcType;

    private String javaType;

    private String column;

    private String property;


    private static Map<String, JdbcTypeAndJavaType> jdbcTypeAndJavaTypeMap;
    static {
        jdbcTypeAndJavaTypeMap = new HashMap<>(33);
        jdbcTypeAndJavaTypeMap.put("CHAR,CHAR", JdbcTypeAndJavaType.CHAR         );
        jdbcTypeAndJavaTypeMap.put("VARCHAR,VARCHAR", JdbcTypeAndJavaType.VARCHAR      );
        //jdbcTypeAndJavaTypeMap.put(",LONG VARCHAR", JdbcTypeAndJavaType.LONGVARCHAR  );
        jdbcTypeAndJavaTypeMap.put("NUMERIC,NUMBER/NUMERIC", JdbcTypeAndJavaType.NUMERIC      );
        jdbcTypeAndJavaTypeMap.put("DECIMAL,DECIMAL", JdbcTypeAndJavaType.DECIMAL      );
        jdbcTypeAndJavaTypeMap.put("BIT,", JdbcTypeAndJavaType.BIT          );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.BOOLEAN      );
        jdbcTypeAndJavaTypeMap.put("TINYINT,", JdbcTypeAndJavaType.TINYINT      );
        jdbcTypeAndJavaTypeMap.put("SMALLINT,SMALLINT", JdbcTypeAndJavaType.SMALLINT     );
        jdbcTypeAndJavaTypeMap.put("INTEGER,INTEGER", JdbcTypeAndJavaType.INTEGER      );
        jdbcTypeAndJavaTypeMap.put("BIGINT,", JdbcTypeAndJavaType.BIGINT       );
        jdbcTypeAndJavaTypeMap.put("REAL,REAL", JdbcTypeAndJavaType.REAL         );
        jdbcTypeAndJavaTypeMap.put("FLOAT,FLOAT", JdbcTypeAndJavaType.FLOAT        );
        jdbcTypeAndJavaTypeMap.put("DOUBLE,NUMBER", JdbcTypeAndJavaType.DOUBLE       );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.BINARY       );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.VARBINARY    );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.LONGVARBINARY);
        jdbcTypeAndJavaTypeMap.put("DATE,DATE", JdbcTypeAndJavaType.DATE         );
        jdbcTypeAndJavaTypeMap.put("TIME,", JdbcTypeAndJavaType.TIME         );
        jdbcTypeAndJavaTypeMap.put("TIMESTAMP/DATETIME,TIMESTAMP", JdbcTypeAndJavaType.TIMESTAMP    );
        jdbcTypeAndJavaTypeMap.put("TEXT,CLOB", JdbcTypeAndJavaType.CLOB         );
        jdbcTypeAndJavaTypeMap.put("BLOB,BLOB", JdbcTypeAndJavaType.BLOB         );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.ARRAY        );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.STRUCT       );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.REF          );
        //jdbcTypeAndJavaTypeMap.put("", JdbcTypeAndJavaType.DATALINK     );
    }

    public static JdbcTypeAndJavaType getJavaTypeAndJdbcType(@NonNull String dbType){
        if(StringUtils.isEmpty(dbType)){
            return null;
        }
        Set<Map.Entry<String, JdbcTypeAndJavaType>> entries = jdbcTypeAndJavaTypeMap.entrySet();
        for (Map.Entry<String, JdbcTypeAndJavaType> entry: entries) {
            if(entry.getKey().contains(dbType.toUpperCase())){
                return entry.getValue();
            }
        }
        return null;
    }

    public enum JdbcTypeAndJavaType{
        CHAR("CHAR", "java.lang.String"),
        VARCHAR("VARCHAR", "java.lang.String"),
        LONGVARCHAR("LONGVARCHAR", "java.lang.String"),
        NUMERIC("NUMERIC","java.math.BigDecimal"),
        DECIMAL("NUMERIC","java.math.BigDecimal"),
        BIT("BIT", "boolean"),
        BOOLEAN("BIT", "boolean"),
        TINYINT("TINYINT","byte"),
        SMALLINT("SMALLINT", "short"),
        INTEGER("INTEGER", "int"),
        BIGINT("BIGINT", "long"),
        REAL("REAL", "float"),
        FLOAT("FLOAT", "float"),
        DOUBLE("DOUBLE","double"),
        BINARY("BINARY", "byte[]"),
        VARBINARY("VARBINARY", "byte[]"),
        LONGVARBINARY("LONGVARBINARY", "byte[]"),
        DATE("DATE", "java.sql.Date"),
        TIME("TIME", "java.sql.Time"),
        TIMESTAMP("TIMESTAMP", "java.sql.Timestamp"),
        CLOB("CLOB", "Clob"),
        BLOB("BLOB", "Blob"),
        ARRAY("ARRAY", "Array"),
        STRUCT("STRUCT", "Struct"),
        REF("REF", "Ref"),
        DATALINK("DATALINK", "java.net.URL");

        private String jdbc;

        private String java;

        public String getJdbc() {
            return jdbc;
        }

        public void setJdbc(String jdbc) {
            this.jdbc = jdbc;
        }

        public String getJava() {
            return java;
        }

        public void setJava(String java) {
            this.java = java;
        }

        JdbcTypeAndJavaType(String jdbc, String java) {
            this.jdbc = jdbc;
            this.java = java;
        }
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "JavaAndJdbcType{" +
                "jdbcType='" + jdbcType + '\'' +
                ", javaType='" + javaType + '\'' +
                ", column='" + column + '\'' +
                ", property='" + property + '\'' +
                '}';
    }
}
