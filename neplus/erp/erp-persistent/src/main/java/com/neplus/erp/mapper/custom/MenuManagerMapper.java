package com.neplus.erp.mapper.custom;


import com.neplus.erp.model.TmMenuPO;

import java.util.List;


public interface MenuManagerMapper
{
	// 查询系统菜单
	public List<TmMenuPO> getMenuList();

}