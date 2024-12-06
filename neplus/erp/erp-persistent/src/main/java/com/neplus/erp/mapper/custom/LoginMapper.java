package com.neplus.erp.mapper.custom;


import com.neplus.erp.model.TmMenuPO;
import com.neplus.erp.model.TmUserPO;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.mybatis.Pager;

import java.util.List;

public interface LoginMapper
{
	List<TmUserPO> selectByUserCode(TmUserPO userPO);

	List<TmUserPO> pageQueryUser(Pager pager);

	List<AclUserBean> selectRoleByUserCode(String userCode);

	List<TmMenuPO> getMenuByRole(Integer roleId);
}