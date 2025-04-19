package com.pzj.schoolrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动态主表
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    /**
     * 作者ID(用户或商家)
     */
    private Long authorId;

    /**
     * 作者类型(1=用户,2=商家)
     */
    private Integer authorType;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 图片URL(JSON数组格式)
     */
    private String images;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 状态(0=草稿,1=已发布,2=已删除,3=违规)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
