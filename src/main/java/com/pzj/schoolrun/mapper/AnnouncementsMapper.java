package com.pzj.schoolrun.mapper;

import com.pzj.schoolrun.entity.Announcements;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统公告表(仅管理员可发) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Mapper
public interface AnnouncementsMapper extends BaseMapper<Announcements> {

}
