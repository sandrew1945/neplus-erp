package com.neplus.erp.mapper.custom;


import com.neplus.erp.model.TmRolePO;
import com.neplus.erp.model.TmUserPO;
import com.neplus.erp.model.TmUserVO;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.mybatis.Pager;

import java.util.List;
import java.util.Map;


public interface UserManagerMapper
{
	List<TmUserPO> userManagerPageQuery(Pager pager);
	
	List<TmRolePO> queryRelationRole(Integer userId);
	
	List<TmRolePO> getRoleExistOwn(AclUserBean userBean);

	List<TmUserVO> getUserListByRoleType(Integer roleType);

	int updateClearAvatar(Map<String, Object> paramMap);
}