package com.pzj.schoolrun.service;

import com.pzj.schoolrun.entity.Tasks;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
