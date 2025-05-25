package com.pzj.schoolrun.mapper;

import com.pzj.schoolrun.entity.Tasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 跑腿任务表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Mapper
public interface TasksMapper extends BaseMapper<Tasks> {
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<Tasks> getByTaskStatus(Integer taskStatus);
}
