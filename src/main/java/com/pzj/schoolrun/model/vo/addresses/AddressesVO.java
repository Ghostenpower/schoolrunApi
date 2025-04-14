package com.pzj.schoolrun.model.vo.addresses;

import lombok.Data;

@Data
public class AddressesVO {
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

}
