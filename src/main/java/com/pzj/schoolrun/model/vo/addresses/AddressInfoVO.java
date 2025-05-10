package com.pzj.schoolrun.model.vo.addresses;

import lombok.Data;

@Data
public class AddressInfoVO {
    private String recipientName;
    private String phone;
    private String detailedAddress;
    private Integer isDefault;
}
