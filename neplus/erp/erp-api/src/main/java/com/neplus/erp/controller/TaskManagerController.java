package com.neplus.erp.controller;

import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientVO;
import com.neplus.erp.bean.taskmanager.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


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

	private static final String ATTACHMENT_PATH = "/attachment";



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
	 * @param base64file
	 * @param filename
	 * @return
	 */
	@PostMapping(value = "uploadAttachment")
	public JsonResult<Integer> uploadAttachment(String base64file, String filename) throws JsonException
	{
		JsonResult<Integer> result = new JsonResult<>();
		try
		{
			TmFilePO filePO = commonService.insertFile(ATTACHMENT_PATH, filename, base64file);
			return result.requestSuccess(filePO.getFileId());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Get the processes list of particular task
	 * @param taskId
	 * @return
	 * @throws JsonException
	 */
	@GetMapping(value = "getTaskProcessList")
	public JsonResult<List<TaskProcessBO>> getTaskProcessList(Integer taskId) throws JsonException
	{
		JsonResult<List<TaskProcessBO>> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(taskManagerService.getTaskProcessList(taskId));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *  Update the task's status to start
	 * @param taskId
	 * @param comment
	 * @param fileId
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "makeTaskStart")
	public JsonResult<Boolean> makeTaskStart(Integer taskId, @RequestParam(required = false) String comment, @RequestParam(required = false) Integer fileId) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(taskManagerService.updateTaskToStart(taskId, comment, fileId));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 *
	 * @param taskId
	 * @param comment
	 * @param fileId
	 * @return
	 * @throws JsonException
	 */
	@PostMapping(value = "makeTaskSelfApproved")
	public JsonResult<Boolean> makeTaskSelfApproved(Integer taskId, @RequestParam(required = false) String comment, @RequestParam(required = false) Integer fileId) throws JsonException
	{
		JsonResult<Boolean> result = new JsonResult<>();
		try
		{
			return result.requestSuccess(taskManagerService.updateTaskToSelfApproved(taskId, comment, fileId));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}
}
