package com.pzj.schoolrun.util;

import org.apache.commons.lang3.StringUtils;

/**
        * SQL 操作工具类
        */
public class SqlUtil {
    private static final String SQL_REGEX = "^[a-zA-Z0-9_\\s,.]*$";

    public static String escapeOrderBySql(String value) {
        return StringUtils.isBlank(value) || !value.matches(SQL_REGEX) ? "" : value;
    }
}