package com.neplus.erp.controller;


import com.neplus.erp.service.MenuManagerService;
import com.neplus.erp.service.util.TreeNode;
import com.neplus.framework.core.exception.JsonException;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.result.JsonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Slf4j
@RestController
@RequestMapping("/menumanager")
public class MenuManagerController extends BaseController
{
	@Resource
	private MenuManagerService menuManagerService;



	@GetMapping("getMenuTree")
	public JsonResult getMenuTree() throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.getMenuTree());
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/createMenu")
	public JsonResult createMenu(TreeNode treeNode, Integer fatherId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.createMenu(treeNode, fatherId, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/updateMenu")
	public JsonResult updateMenu(TreeNode treeNode) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.updateMenu(treeNode, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deleteMenu")
	public JsonResult deleteMenu(Integer functionId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.deleteMenuById(functionId, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}


}
