package com.neplus.erp.service;

import com.neplus.erp.bean.RoleBean;
import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientManagerDTO;
import com.neplus.erp.bean.usermanager.UserManagerBO;
import com.neplus.erp.bean.usermanager.UserManagerDTO;
import com.neplus.erp.model.TmClientPO;
import com.neplus.erp.model.TmUserPO;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface ClientManagerService extends BaseService
{
	/**
	 * Paginated query of the clients list
	 * @param condition
	 * @param roleType
	 * @param limit
	 * @param curPage
	 * @return
	 * @throws ServiceException
	 */
	PageResult<ClientManagerBO> clientManagerPageQuery(ClientManagerDTO condition, Integer roleType, int limit, int curPage) throws ServiceException;

	/**
	 *	Get the information of particular client
	 * @param clientId
	 * @return
	 * @throws ServiceException
	 */
	ClientManagerBO getClientInfoById(Integer clientId) throws ServiceException;

	/**
	 *  Create a new client
	 * @param client
	 * @return
	 * @throws ServiceException
	 */
	Boolean createClientInfo(ClientManagerDTO client) throws ServiceException;

	/**
	 *  Update a client information by client ID
	 * @param client
	 * @return
	 * @throws ServiceException
	 */
	Boolean updateClientInfoById(ClientManagerDTO client) throws ServiceException;

	/**
	 *  Check the client name was existed or not
	 * @param clientName
	 * @return
	 * @throws ServiceException
	 */
	Boolean validateClientName(String clientName) throws ServiceException;

	/**
	 *  Delete the particular client information by id
	 * @param clientId
	 * @return
	 * @throws ServiceException
	 */
	Boolean deleteClient(Integer clientId) throws ServiceException;
}
