package com.pzj.schoolrun.model.vo.addresses;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AddressesUpdateVO {
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
}
