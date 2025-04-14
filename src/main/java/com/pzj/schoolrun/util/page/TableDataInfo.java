package com.pzj.schoolrun.util.page;

import lombok.Data;

import java.util.List;

@Data
public class TableDataInfo {
    private Integer code;    // 状态码
    private String msg;     // 消息
    private Long total;      // 总记录数
    private List<?> rows;   // 数据列表

    public TableDataInfo() {
        this.code = 200;
        this.msg = "操作成功";
    }
}