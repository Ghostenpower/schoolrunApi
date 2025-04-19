package com.pzj.schoolrun.controller;


import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.tasks.TasksAddVO;
import com.pzj.schoolrun.model.vo.tasks.TasksUpdateVO;
import com.pzj.schoolrun.service.ITasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
public class TasksController extends BaseController {
    @Autowired
    private ITasksService tasksService;

    @GetMapping("/getList")
    public Result<?> list() {
        startPage();
        //Long userId = getUserId();
        Long userId = 1L;
        List<Tasks> list = tasksService.getByUserId(userId);
        return Result.success(PageInfo.of(list));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody TasksAddVO tasksAddVO) {
        //Long userId = getUserId();
        Long userId = 1L;
        Tasks tasks = Tasks.builder()
                .userId(userId)
                .taskType(tasksAddVO.getTaskType())
                .title(tasksAddVO.getTitle())
                .description(tasksAddVO.getDescription())
                .pickupLocation(tasksAddVO.getPickupLocation())
                .pickupCoordinates(tasksAddVO.getPickupCoordinates())
                .deliveryLocation(tasksAddVO.getDeliveryLocation())
                .deliveryCoordinates(tasksAddVO.getDeliveryCoordinates())
                .deadline(tasksAddVO.getDeadline())
                .imagesUrl(tasksAddVO.getImagesUrl())
                .price(tasksAddVO.getPrice())
                .status(0)
                .paymentStatus(0)
                .remark(tasksAddVO.getRemark())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        tasksService.save(tasks);
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody TasksUpdateVO tasksUpdateVO) {
        Tasks tasks = Tasks.builder()
                .taskId(tasksUpdateVO.getTaskId())
                .taskType(tasksUpdateVO.getTaskType())
                .title(tasksUpdateVO.getTitle())
                .description(tasksUpdateVO.getDescription())
                .pickupLocation(tasksUpdateVO.getPickupLocation())
                .pickupCoordinates(tasksUpdateVO.getPickupCoordinates())
                .deliveryLocation(tasksUpdateVO.getDeliveryLocation())
                .deliveryCoordinates(tasksUpdateVO.getDeliveryCoordinates())
                .deadline(tasksUpdateVO.getDeadline())
                .remark(tasksUpdateVO.getRemark())
                .updatedAt(LocalDateTime.now())
                .build();

        tasksService.updateById(tasks);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestBody Long taskId) {
        Tasks tasks = tasksService.getById(taskId);
        if (tasks == null) {
            return Result.error("任务不存在");
        }
        tasks.setStatus(4);
        tasksService.updateById(tasks);
        return Result.success();
    }

}
