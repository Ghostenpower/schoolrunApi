package com.pzj.schoolrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.schoolrun.entity.Tasks;

import java.util.List;

/**
 * <p>
 * 跑腿任务表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
public interface ITasksService extends IService<Tasks> {

    List<Tasks> getByUserId(Long userId);
    boolean updateTaskStatus(Long taskId, Integer status, Long courierId);

    /**
     * 根据跑腿员ID查询任务列表
     */
    List<Tasks> getByCourierId(Long courierId);

    List<Tasks> searchByTitle(String keyword);

    List<Tasks> getByTaskStatus(Integer taskStatus, Integer tasksType);
}
