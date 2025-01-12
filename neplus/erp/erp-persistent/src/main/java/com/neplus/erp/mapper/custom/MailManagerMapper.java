package com.neplus.erp.mapper.custom;


import com.neplus.erp.bean.mailmanager.MailManagerBO;
import com.neplus.framework.core.mybatis.Pager;

import java.util.List;


public interface MailManagerMapper
{
	/**
	 *  Paginated query of the mail template list
	 * @param pager
	 * @return
	 */
	List<MailManagerBO> mailManagerPageQuery(Pager pager);

}