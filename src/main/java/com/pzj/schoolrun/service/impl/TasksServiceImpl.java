package com.pzj.schoolrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.mapper.TasksMapper;
import com.pzj.schoolrun.service.ITasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private TasksMapper tasksMapper;

    @Override
    public List<Tasks> getByUserId(Long userId) {
        LambdaQueryWrapper<Tasks> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.eq(Tasks::getUserId,userId);
        return tasksMapper.selectList(queryWrap);
    }
}
