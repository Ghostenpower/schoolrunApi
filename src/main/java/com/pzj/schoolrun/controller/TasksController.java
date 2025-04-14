package com.pzj.schoolrun.controller;


import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.tasks.TasksAddVO;
import com.pzj.schoolrun.model.vo.tasks.TasksUpdateVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 跑腿任务表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/tasks")
public class TasksController {
    @PostMapping("/add")
    public Result<?> add(@RequestBody TasksAddVO tasksAddVO) {
        return null;
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody TasksUpdateVO tasksUpdateVO) {
        return null;
    }

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestBody Long taskId) {
        return null;
    }

}
