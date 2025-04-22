package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Announcements;
import com.pzj.schoolrun.mapper.AnnouncementsMapper;
import com.pzj.schoolrun.service.IAnnouncementsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统公告表(仅管理员可发) 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements> implements IAnnouncementsService {

}
