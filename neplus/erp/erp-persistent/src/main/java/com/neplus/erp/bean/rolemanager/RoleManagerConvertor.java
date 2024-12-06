package com.neplus.erp.bean.rolemanager;

import com.neplus.erp.bean.RoleBean;
import com.neplus.erp.model.TmRolePO;
import com.neplus.erp.model.TmRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface RoleManagerConvertor
{
    @Mapping(source = "tmRolePO.roleType", target = "roleType")
    @Mapping(source = "tmRolePO.roleStatus", target = "roleStatus")
    TmRoleVO toRoleVO(TmRolePO tmRolePO);

    List<TmRoleVO> toRoleVOList(List<RoleBean> roleBeans);

    List<RoleBean> toRoleBOList(List<TmRolePO> roles);
}
