package com.pzj.schoolrun.util.page;

import com.github.pagehelper.PageHelper;
import com.pzj.schoolrun.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtils {
    public static void startPage() {
        String pageNumStr = ServletUtils.getParameter("pageNum");
        String pageSizeStr = ServletUtils.getParameter("pageSize");
        String orderByStr = ServletUtils.getParameter("orderBy");

        Integer pageNum = parseWithDefault(pageNumStr, 1);
        Integer pageSize = parseWithDefault(pageSizeStr, 10);
        String orderBy = parseOrderBy(orderByStr); // 解析并校验排序字段

        log.info("分页参数: pageNum={}, pageSize={}, orderBy={}", pageNum, pageSize, orderBy);
        PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    private static String parseOrderBy(String orderByStr) {
        if (orderByStr == null || orderByStr.isEmpty()) {
            return "";
        }

        // 替换特殊符号为空格（兼容无空格URL）
        String normalized = orderByStr.replace("-", " ");
        String[] parts = normalized.trim().split("\\s+");
        if (parts.length == 0) {
            return "";
        }

        // 获取字段名并转换（驼峰→下划线）
        String field = parts[0];
        field = mapFieldName(field);

        // 获取排序方向
        String direction = (parts.length >= 2) ? parts[1].toUpperCase() : "ASC";
        if (!"ASC".equals(direction) && !"DESC".equals(direction)) {
            direction = "ASC"; // 默认升序
        }

        return field + " " + direction;
    }

    // 字段名映射（驼峰→下划线）
    private static String mapFieldName(String field) {
        if ("userId".equalsIgnoreCase(field)) {
            return "user_id";
        }
        return field; // 其他字段原样返回
    }

    // 解析整数参数
    private static Integer parseWithDefault(String str, Integer defaultValue) {
        return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : defaultValue;
    }

    public static void clearPage() {
        PageHelper.clearPage();
    }
}
