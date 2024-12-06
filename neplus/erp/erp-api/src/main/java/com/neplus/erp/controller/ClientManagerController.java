/**********************************************************************
* <pre>
* FILE : RoleManagerController.java
* CLASS : RoleManagerController
*
* AUTHOR : Liutt
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年5月30日| Liutt| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: RoleManagerController.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
*/
package com.neplus.erp.controller;

import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientManagerConvertor;
import com.neplus.erp.bean.clientmanager.ClientManagerDTO;
import com.neplus.erp.bean.clientmanager.ClientVO;
import com.neplus.erp.bean.usermanager.UserManagerBO;
import com.neplus.erp.model.TmClientPO;
import com.neplus.erp.model.TmRolePO;
import com.neplus.erp.param.FunctionsParam;
import com.neplus.erp.service.ClientManagerService;
import com.neplus.erp.service.RoleManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.JsonException;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.result.JsonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Slf4j
@RestController
@RequestMapping("/clientmanager")
public class ClientManagerController extends BaseController
{
	@Resource
	private ClientManagerService clientManagerService;
	@Resource
	private ClientManagerConvertor clientManagerConvertor;

	@PostMapping(value = "/clientManagerPageQuery")
	public JsonResult<PageResult<ClientManagerBO>> clientManagerPageQuery(@RequestParam(required = false) String clientName, @RequestParam(required = false) Integer clientType,
														 @RequestParam(required = false) Integer optId, @RequestParam(required = false) Integer declarePeriod,
														 @RequestParam(required = false) Integer approveId, int limit, int curPage) throws JsonException
	{
		JsonResult<PageResult<ClientManagerBO>> result = new JsonResult<>();
		try
		{
			ClientManagerDTO condition = new ClientManagerDTO();
			condition.setClientName(clientName);
			condition.setClientType(clientType);
			condition.setOptId(optId);
			condition.setDeclarePeriod(declarePeriod);
			condition.setApproveId(approveId);
			PageResult<ClientManagerBO> pr = clientManagerService.clientManagerPageQuery(condition, getLoginUser().getRoleType(), limit, curPage);
			return result.requestSuccess(pr.convert(ClientVO.class));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  根据客户ID获取客户信息
	 * @param clientId
	 * @return
	 * @throws JsonException
	 */
	@GetMapping("getClientInfoById")
	public JsonResult<ClientVO> getClientInfoById(Integer clientId) throws JsonException
	{
		JsonResult<ClientVO> result = new JsonResult<>();
		try
		{
			ClientManagerBO clientManagerBO = clientManagerService.getClientInfoById(clientId);
			result.requestSuccess(clientManagerConvertor.toClientVO(clientManagerBO));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 *  Create a new client
	 * @param client
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "createClientInfo")
	public JsonResult<Boolean> createClientInfo(ClientManagerDTO client) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(clientManagerService.createClientInfo(client));
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Update the client information by id
	 * @param client
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "updateClientInfo")
	public JsonResult<Boolean> updateClientInfo(ClientManagerDTO client) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(clientManagerService.updateClientInfoById(client));
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 * 	Check the client name was existed or not
	 * @param clientName
	 * @return
	 * @throws JsonException
	 */
	@GetMapping(value = "clientNameValidate")
	public JsonResult<Boolean> clientNameValidate(String clientName) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(clientManagerService.validateClientName(clientName));
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Delete the particular client information by id
	 * @param clientId
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "/deleteClient")
	public JsonResult<Boolean> deleteClient(Integer clientId) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(clientManagerService.deleteClient(clientId));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}
}
