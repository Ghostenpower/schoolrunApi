package com.pzj.schoolrun.model.vo.users;


import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceOperationVO {
    @DecimalMin(value = "0.01", message = "金额需大于0")
    private BigDecimal balance;
}
