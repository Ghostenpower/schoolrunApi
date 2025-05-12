package com.pzj.schoolrun.mapper;

import com.pzj.schoolrun.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    int insert(Feedback feedback);
    List<Feedback> selectAll();
    Feedback selectById(Long id);
}
