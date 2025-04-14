package com.pzj.schoolrun.model.vo.merchants;

import lombok.Data;

@Data
public class MerchantRegisterVO {
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
}
