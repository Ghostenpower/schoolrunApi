package com.pzj.schoolrun.controller;

import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.util.ServletUtils;
import com.pzj.schoolrun.util.ThreadLocalUntil;
import com.pzj.schoolrun.util.page.PageUtils;

import java.util.List;

public class BaseController {
    /**
            * 设置分页参数
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    protected  Long getUserId(){
        return ThreadLocalUntil.getUserId();
    }

    protected  void clearPage() {
        PageUtils.clearPage();
    }

    /**
            * 封装分页结果
     */
    protected PageInfo getDataTable(List<?> list) {
        return PageInfo.of(list);
    }
}
