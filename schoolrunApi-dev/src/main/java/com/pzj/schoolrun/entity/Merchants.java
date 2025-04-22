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
 * 商家信息表
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("merchants")
public class Merchants implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家ID
     */
    @TableId(value = "merchant_id", type = IdType.AUTO)
    private Long merchantId;

    /**
     * 关联用户ID
     */
    private Long userId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺LOGO URL
     */
    private String shopLogo;

    /**
     * 营业执照URL
     */
    private String businessLicense;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 店铺描述
     */
    private String description;

    /**
     * 商家类别
     */
    private String category;

    /**
     * 状态(0待审核,1已通过,2已拒绝)
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
