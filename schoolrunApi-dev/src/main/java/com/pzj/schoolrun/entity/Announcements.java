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
 * 系统公告表(仅管理员可发)
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("announcements")
public class Announcements implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Long announcementId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 管理员ID
     */
    private Long adminId;

    /**
     * 公告类型(1=系统公告,2=活动通知,3=规则变更)
     */
    private Integer announcementType;

    /**
     * 生效时间
     */
    private LocalDateTime startTime;

    /**
     * 失效时间
     */
    private LocalDateTime endTime;

    /**
     * 优先级(0=普通,1=重要,2=紧急)
     */
    private Integer priority;

    /**
     * 状态(0=草稿,1=已发布,2=已下线)
     */
    private Integer status;

    /**
     * 目标受众(0=全部,1=用户,2=跑腿员,3=商家)
     */
    private Integer targetAudience;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
