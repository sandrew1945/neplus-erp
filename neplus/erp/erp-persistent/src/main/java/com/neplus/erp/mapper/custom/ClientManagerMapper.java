package com.neplus.erp.mapper.custom;


import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.model.TmClientPO;
import com.neplus.framework.core.mybatis.Pager;

import java.util.List;


public interface ClientManagerMapper
{
	/**
	 *  客户管理分页查询
	 * @param pager
	 * @return
	 */
	public List<ClientManagerBO> clientManagerPageQuery(Pager pager);

}