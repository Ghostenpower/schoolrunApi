package com.pzj.schoolrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地址信息表
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("addresses")
@Builder
public class Addresses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @TableId(value = "address_id", type = IdType.AUTO)
    private Long addressId;

    /**
     * 关联用户ID
     */
    private Long userId;

    /**
     * 地址类型(1=用户地址,2=商家地址)
     */
    private Integer addressType;

    /**
     * 收件人姓名
     */
    private String recipientName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 是否默认地址(0=否,1=是)
     */
    private Integer isDefault;

    /**
     * 状态(0=禁用,1=正常)
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
