package com.neplus.erp.mapper.custom;


import com.neplus.erp.bean.RoleBean;
import com.neplus.erp.model.TrUserRolePO;
import com.neplus.framework.core.mybatis.Pager;

import java.util.List;


/**
 * 角色管理mapper
 * @author liutingting
 *
 */
public interface RoleManagerMapper
{
	public List<RoleBean> roleManagerPageQuery(Pager pager);
	/**
	 * 
	 * Function    : 根据角色id查询用户角色关系表
	 * LastUpdate  : 2016年5月31日
	 * @param roleId
	 * @return
	 */
	public List<TrUserRolePO> userRoleByRoleId(Integer roleId);

}