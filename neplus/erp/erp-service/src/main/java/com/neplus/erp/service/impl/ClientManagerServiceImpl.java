/**
 * <pre>
 * FILE : LoginService.java
 * CLASS : LoginService
 *
 * AUTHOR : SuMMeR
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2014年5月3日| SuMMeR| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */
/**
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */

package com.neplus.erp.service.impl;


import com.neplus.erp.bean.RoleBean;
import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientManagerConvertor;
import com.neplus.erp.bean.clientmanager.ClientManagerDTO;
import com.neplus.erp.bean.rolemanager.RoleManagerConvertor;
import com.neplus.erp.bean.usermanager.UserManagerBO;
import com.neplus.erp.bean.usermanager.UserManagerConvertor;
import com.neplus.erp.bean.usermanager.UserManagerDTO;
import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TmClientPOMapper;
import com.neplus.erp.mapper.TmRolePOMapper;
import com.neplus.erp.mapper.TmUserPOMapper;
import com.neplus.erp.mapper.TrUserRolePOMapper;
import com.neplus.erp.mapper.custom.ClientManagerMapper;
import com.neplus.erp.mapper.custom.UserManagerMapper;
import com.neplus.erp.model.*;
import com.neplus.erp.service.ClientManagerService;
import com.neplus.erp.service.UserManagerService;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.encrypt.MD5Encrypt;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.exception.TooManyResultsException;
import com.neplus.framework.core.mybatis.PageQueryBuilder;
import com.neplus.framework.core.util.MagicOOO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *  客户管理Service
 *
 */
@Service
@Slf4j
public class ClientManagerServiceImpl implements ClientManagerService
{

    @Resource
    private ClientManagerMapper clientManagerMapper;
    @Resource
    private TmClientPOMapper tmClientPOMapper;
    @Resource
    private ClientManagerConvertor clientManagerConvertor;

    @Override
    public PageResult<ClientManagerBO> clientManagerPageQuery(ClientManagerDTO condition, Integer roleType, int limit, int curPage) throws ServiceException
    {
        try
        {
            // judge the role, if the role type is staff, only show the client they are responsible for.
            if (Fixcode.ROLE_TYPE_STAFF.fixcode.equals(roleType))
            {
                condition.setOptId(getLoginUser().getUserId());
            }
            return PageQueryBuilder.pageQuery(clientManagerMapper, "clientManagerPageQuery", condition, curPage, limit);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to query the clients list.", e);
        }
    }

    @Override
    public ClientManagerBO getClientInfoById(Integer clientId) throws ServiceException
    {
        try
        {
            TmClientPO clientPO = tmClientPOMapper.selectByPrimaryKey(clientId);
            return clientManagerConvertor.toClientManagerBO(clientPO);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to get the client information.", e);
        }
    }

    @Override
    public Boolean createClientInfo(ClientManagerDTO client) throws ServiceException
    {
        try
        {
            if (null != client)
            {
                TmClientPO clientPO = clientManagerConvertor.toClientPO(client);
                // Check the logged-in user's role type, if role type is staff, set the operator to themselves.
                if (getLoginUser().getRoleType().equals(Fixcode.ROLE_TYPE_STAFF.fixcode))
                {
                    clientPO.setOptId(getLoginUser().getUserId());
                }
                clientPO.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
                clientPO.setCreateBy(getLoginUser().getUserName());
                clientPO.setCreateDate(new Date());
                int count = tmClientPOMapper.insertSelective(clientPO);
                return count > 0;
            }
            return false;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to create a new client.", e);
        }
    }

    @Override
    public Boolean updateClientInfoById(ClientManagerDTO client) throws ServiceException
    {
        try
        {
            if (null != client)
            {
                TmClientPO clientPO = clientManagerConvertor.toClientPO(client);
                clientPO.setUpdateBy(getLoginUser().getUserName());
                clientPO.setUpdateDate(new Date());
                int count = tmClientPOMapper.updateByPrimaryKeySelective(clientPO);
                return count > 0;
            }
            return false;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the particular client information.", e);
        }
    }

    @Override
    public Boolean validateClientName(String clientName) throws ServiceException
    {
        try
        {
            TmClientPOExample example = new TmClientPOExample();
            TmClientPOExample.Criteria criteria = example.createCriteria();
            criteria.andClientNameEqualTo(clientName);
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.fixcode);
            List<TmClientPO> clientPOS = tmClientPOMapper.selectByExample(example);
            return null == clientPOS || clientPOS.isEmpty();
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to check the client name[" + clientName + "].", e);
        }
    }

    @Override
    public Boolean deleteClient(Integer clientId) throws ServiceException
    {
        try
        {
            TmClientPO condition = new TmClientPO();
            condition.setClientId(clientId);
            condition.setIsDelete(Fixcode.IF_TYPE_YES.fixcode);
            condition.setUpdateDate(new Date());
            condition.setUpdateBy(getLoginUser().getUserCode());
            int count = tmClientPOMapper.updateByPrimaryKeySelective(condition);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to delete the client.", e);
        }
    }
}
