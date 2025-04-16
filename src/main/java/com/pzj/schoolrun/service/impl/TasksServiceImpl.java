package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.mapper.TasksMapper;
import com.pzj.schoolrun.service.ITasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 跑腿任务表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements ITasksService {

}
