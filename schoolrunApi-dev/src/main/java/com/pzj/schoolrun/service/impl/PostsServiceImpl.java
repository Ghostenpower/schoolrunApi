package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Posts;
import com.pzj.schoolrun.mapper.PostsMapper;
import com.pzj.schoolrun.service.IPostsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态主表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {

}
