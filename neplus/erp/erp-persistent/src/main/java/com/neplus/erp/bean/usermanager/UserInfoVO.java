package com.neplus.erp.bean.usermanager;

import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.model.TmRoleVO;
import com.neplus.framework.core.annotation.EnumHandler;
import com.neplus.framework.core.bean.VO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class UserInfoVO implements VO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String roleName;

    private String roleCode;

    private Date birthday;

    private String avatar;

    @EnumHandler(Fixcode.class)
    private String sex;

    private String phone;

    private String mobile;

    private String email;

    private List<TmRoleVO> roleList;

    @EnumHandler(Fixcode.class)
    private String userStatus;
}
