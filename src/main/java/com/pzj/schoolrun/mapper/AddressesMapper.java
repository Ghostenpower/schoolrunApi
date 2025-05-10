package com.pzj.schoolrun.mapper;

import com.pzj.schoolrun.entity.Addresses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzj.schoolrun.model.vo.addresses.AddressInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 地址信息表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Mapper
public interface AddressesMapper extends BaseMapper<Addresses> {
    /**
     * 根据 addressId 查询指定字段
     */
    @Select("SELECT recipient_name, phone, detailed_address, is_default " +
            "FROM addresses WHERE address_id = #{addressId}")
    AddressInfoVO getAddressInfoById(@Param("addressId") Long addressId);


}
