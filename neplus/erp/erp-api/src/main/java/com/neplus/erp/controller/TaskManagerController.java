package com.neplus.erp.controller;

import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientVO;
import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerConvertor;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskVO;
import com.neplus.erp.model.TmFilePO;
import com.neplus.erp.service.ClientManagerService;
import com.neplus.erp.service.CommonService;
import com.neplus.erp.service.TaskManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.JsonException;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.result.JsonResult;
import com.neplus.framework.core.util.DateTimeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 *  Task management controllers
 */
@Slf4j
@RestController
@RequestMapping("/taskmanager")
public class TaskManagerController extends BaseController
{
	@Resource
	private TaskManagerService taskManagerService;
	@Resource
	private TaskManagerConvertor taskManagerConvertor;
	@Resource
	private CommonService commonService;



	/**
	 *  Paginated query the tasks list by particular conditions.
	 * @param clientName
	 * @param clientType
	 * @param taskType
	 * @param taskStatus
	 * @param startFrom
	 * @param endWith
	 * @param limit
	 * @param curPage
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "/taskManagerPageQuery")
	public JsonResult<PageResult<TaskManagerBO>> clientManagerPageQuery(@RequestParam(required = false) String clientName, @RequestParam(required = false) Integer clientType,
																		  @RequestParam(required = false) Integer taskType, @RequestParam(required = false) Integer taskStatus,
																		  @RequestParam(required = false) Date startFrom, @RequestParam(required = false) Date endWith, int limit, int curPage) throws JsonException
	{
		JsonResult<PageResult<TaskManagerBO>> result = new JsonResult<>();
		try
		{
			TaskManagerDTO condition = new TaskManagerDTO();
			condition.setClientName(clientName);
			condition.setClientType(clientType);
			condition.setTaskType(taskType);
			condition.setTaskStatus(taskStatus);
			condition.setStartFrom(DateTimeUtil.parseDateToDate(startFrom));
			condition.setEndWith(DateTimeUtil.parseDateToDate(endWith));
			PageResult<TaskManagerBO> pr = taskManagerService.taskManagerPageQuery(condition, getLoginUser().getRoleType(), limit, curPage);
			return result.requestSuccess(pr.convert(TaskVO.class));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Fetch the particular task information base on the task id
	 * @param taskId
	 * @return
	 * @throws JsonException
	 */
	@GetMapping(value = "getTaskInfoById")
	public JsonResult<TaskVO> getTaskInfo(Integer taskId) throws JsonException
	{
		JsonResult<TaskVO> result = new JsonResult<>();
		try
		{
			TaskManagerBO taskInfoBO = taskManagerService.getTaskInfo(taskId);
			result.requestSuccess(taskManagerConvertor.toTaskVO(taskInfoBO));
			return result;
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}

	}

	/**
	 * Delete the particular task by id
	 * @param taskId
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "deleteTaskById")
	public JsonResult<Boolean> deleteTaskById(Integer taskId) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return  result.requestSuccess(taskManagerService.deleteTaskById(taskId));
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Upload the attachment and update the corresponding field in tm_task based on the attachmentType.
	 * @param taskId
	 * @param attachmentType
	 * @param base64file
	 * @param filename
	 * @return
	 */
	@PostMapping(value = "uploadAttachment")
	public JsonResult<String> uploadAttachment(Integer taskId, Integer attachmentType, String base64file, String filename)
	{
		JsonResult<String> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(taskManagerService.uploadAttachment(taskId, attachmentType, base64file, filename));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("图片上传失败", e);
		}
	}

}
