package com.pzj.schoolrun.model.vo.tasks;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TasksAddVO {
    /**
     * 任务类型(1=代取快递,2=代购餐食,3=文件打印,4=其他)
     */
    private Integer taskType;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务详细描述
     */
    private String description;

    /**
     * 取件地点
     */
    private String pickupLocation;

    /**
     * 取件坐标
     */
    private String pickupCoordinates;

    /**
     * 送达地点
     */
    private String deliveryLocation;

    /**
     * 送达坐标
     */
    private String deliveryCoordinates;

    /**
     * 任务截止时间
     */
    private LocalDateTime deadline;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 图片列表url(逗号分隔)
     */
    private String imagesUrl;
}
