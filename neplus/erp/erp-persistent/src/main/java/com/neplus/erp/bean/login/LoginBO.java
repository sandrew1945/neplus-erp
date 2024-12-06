package com.neplus.erp.bean.login;

import com.neplus.framework.core.bean.BO;
import lombok.Data;

/**
 * @ClassName LoginBO
 * @Description
 * @Author summer
 * @Date 2023/3/1 13:52
 **/
@Data
public class LoginBO implements BO
{
    private Integer userId;

    private String token;
}
